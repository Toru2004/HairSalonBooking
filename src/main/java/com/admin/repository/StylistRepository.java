    package com.admin.repository;

    import com.admin.model.Appointment;
    import com.admin.model.Stylist;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.CrudRepository;
    import org.springframework.data.repository.query.Param;

    import java.util.List;

    public interface StylistRepository extends CrudRepository<Stylist, Integer> {
        Long countById(Integer id);
        // Thêm phương thức để tìm stylist với enabled = true
        List<Stylist> findAllByUserEnabled(boolean enabled);
        @Query("SELECT a FROM Appointment a WHERE a.stylist.user.username = :username")
        List<Appointment> findByStylistUsername(@Param("username") String username);

    }
