    package com.admin.service;

    import java.util.ArrayList;
    import com.admin.model.Stylist;
    import com.admin.repository.StylistRepository;
    import com.admin.exception.StylistNotFoundException;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;
    import java.util.Collection; // Thêm import cho Collection
    @Service
    public class StylistService {

<<<<<<< HEAD
            @Autowired
            private StylistRepository stylistRepository;

        public List<Stylist> getAllStylists() {
            // Chuyển Iterable thành List
            Iterable<Stylist> stylistIterable = stylistRepository.findAll();
            return new ArrayList<>((Collection<? extends Stylist>) stylistIterable);
        }

=======
    @Autowired
    private StylistRepository stylistRepository;

    public List<Stylist> getAllStylists() {
        // Chuyển Iterable thành List
        Iterable<Stylist> stylistIterable = stylistRepository.findAll();
        return new ArrayList<>((Collection<? extends Stylist>) stylistIterable);
    }


    public List<Stylist> listAll() {
        return (List<Stylist>) stylistRepository.findAll();
    }

    public void save(Stylist stylist) {
        stylistRepository.save(stylist);
    }
>>>>>>> afa7c39f4be4628f8c584a20b4fdeb0ce6995100

            public List<Stylist> listAll() {
                return (List<Stylist>) stylistRepository.findAll();
            }

            public void save(Stylist stylist) {
                stylistRepository.save(stylist);
            }

        public Stylist get(Integer id) throws StylistNotFoundException {
            Optional<Stylist> result = stylistRepository.findById(id);
            if (result.isPresent()) {
                return result.get();
            }
            throw new StylistNotFoundException("Could not find any stylist with ID " + id);
        }

        public void delete(Integer id) throws StylistNotFoundException {
            Long count = stylistRepository.countById(id);
            if (count == null || count == 0) {
                throw new StylistNotFoundException("Could not find any stylist with ID " + id);
            }
            stylistRepository.deleteById(id);
        }
    }
<<<<<<< HEAD
=======
}
>>>>>>> afa7c39f4be4628f8c584a20b4fdeb0ce6995100
