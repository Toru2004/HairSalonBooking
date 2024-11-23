package com.admin.controller;

import com.admin.exception.CareNotFoundException;
import com.admin.exception.StylistNotFoundException;
import com.admin.model.Care;
import com.admin.model.Stylist;
import com.admin.service.CareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;



// Được sử dụng trong các phương thức controller để nhận dữ liệu từ form, hoặc truyền dữ liệu vào đối tượng model
import org.springframework.web.bind.annotation.ModelAttribute;

// Được sử dụng trong các phương thức controller để nhận các tham số từ form (như tệp tải lên)
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;
import java.util.Comparator;


@Controller
public class CareController {
    @Autowired
    private CareService careService;

    @GetMapping("/page/services")
    public String showServices(HttpServletRequest request,Model model) {
        List<Care> listCares = careService.listAll(); // Loại bỏ stream() và sorted()
        model.addAttribute("listCares", listCares);
        model.addAttribute("title", "Our Services");

//        // Lấy vai trò từ session
//        String role = (String) request.getSession().getAttribute("role");
//
//        // Kiểm tra nếu không phải staff thì chuyển hướng
//        if (role == null || !role.equals("admin")) {
//            return "redirect:/page/login"; // Chuyển hướng đến trang Access Denied
//        }
        return "view/pages/services";
    }



    @GetMapping("/manageCares/image/{id}")
    public ResponseEntity<byte[]> getCareImage(@PathVariable("id") Integer id) throws CareNotFoundException {
        Care care = careService.get(id); // Gọi service để lấy dữ liệu
        byte[] image = care.getProfilePicture();
        if (image == null) {
            throw new CareNotFoundException("Image not found for care with ID " + id);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Định dạng ảnh
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }


    @GetMapping("/service/showServices")
    public String showServiceList(@RequestParam(value = "search", required = false) String searchQuery, Model model) {
        // Kiểm tra nếu có tham số tìm kiếm
        List<Care> listCares;
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            // Tìm kiếm staff theo tên
            listCares = careService.searchByName(searchQuery);
            model.addAttribute("searchQuery", searchQuery);
        } else {
            // Nếu không có tìm kiếm, lấy tất cả staff
            listCares = careService.listAll();
        }

//        List<Care> listCares = careService.listAll();
        model.addAttribute("listCares", listCares);
        return "view/pages/services"; // Trả về view manageCares.html
    }

    // Hiển thị danh sách tất cả care
    @GetMapping("/manageCares")
    public String showCareList(Model model) {
        List<Care> listCares = careService.listAll();
        model.addAttribute("listCares", listCares);
        return "admin/manageCares"; // Trả về view manageCares.html
    }

    // Hiển thị form để thêm care mới
    @GetMapping("/manageCares/new")
    public String showNewCareForm(Model model) {
        model.addAttribute("care", new Care()); // Tạo đối tượng Care mới
        model.addAttribute("pageTitle", "Add New Service");
        return "admin/care_form"; // Trả về template care_form.html
    }


    @PostMapping("/manageCares/save")
    public String saveCare(
            @ModelAttribute Care care,
            @RequestParam("imageFile") MultipartFile imageFile,
            RedirectAttributes ra) {
        try {
            if (care.getId() != null) { // Nếu có ID thì thực hiện cập nhật
                Care existingCare = careService.get(care.getId());
                existingCare.setName(care.getName());
                existingCare.setDescription(care.getDescription());
                existingCare.setPrice(care.getPrice());
                if (!imageFile.isEmpty()) {
                    existingCare.setProfilePicture(imageFile.getBytes()); // Cập nhật ảnh
                }
                careService.save(existingCare); // Lưu Care đã chỉnh sửa
            } else {
                // Nếu không có ID, thêm mới
                if (!imageFile.isEmpty()) {
                    care.setProfilePicture(imageFile.getBytes());
                }
                careService.save(care);
            }
            ra.addFlashAttribute("message", "The care has been saved successfully.");
        } catch (IOException e) {
            ra.addFlashAttribute("message", "Error uploading image: " + e.getMessage());
        } catch (CareNotFoundException e) {
            ra.addFlashAttribute("message", "Failed to save the care: " + e.getMessage());
        }
        return "redirect:/manageCares";
    }


    // Hiển thị form để chỉnh sửa thông tin care
    @GetMapping("/manageCares/edit/{id}")
    public String showEditCareForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Care care = careService.get(id); // Lấy đối tượng Care theo ID
            model.addAttribute("care", care); // Truyền Care vào Model
            model.addAttribute("pageTitle", "Edit Service (ID: " + id + ")");
            return "admin/care_form"; // Trả về template care_form.html
        } catch (CareNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/manageCares"; // Chuyển hướng nếu không tìm thấy Care
        }
    }


    // Xóa care
    @GetMapping("/manageCares/delete/{id}")
    public String deleteCare(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            careService.delete(id); // Gọi phương thức xóa care
            ra.addFlashAttribute("message", "The care has been deleted successfully.");
        } catch (Exception e) {
            ra.addFlashAttribute("message", "Error: " + e.getMessage());
        }
        return "redirect:/manageCares"; // Chuyển hướng về trang danh sách care
    }
}
