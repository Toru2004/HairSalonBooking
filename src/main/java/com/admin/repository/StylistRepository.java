    package com.admin.repository;

    import com.admin.model.Stylist;
    import org.springframework.data.repository.CrudRepository;
    import java.util.List;

    public interface StylistRepository extends CrudRepository<Stylist, Integer> {
        Long countById(Integer id);
        // Thêm phương thức để tìm stylist với enabled = true
        List<Stylist> findAllByUserEnabled(boolean enabled);
    }
