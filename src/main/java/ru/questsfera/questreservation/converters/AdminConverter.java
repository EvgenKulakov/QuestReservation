package ru.questsfera.questreservation.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.questsfera.questreservation.repository.AdminRepository;
import ru.questsfera.questreservation.entity.Admin;

import java.util.Optional;

@Component
public class AdminConverter implements Converter<String, Admin> {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminConverter(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin convert(String source) {
        Integer id = Integer.parseInt(source);

        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (optionalAdmin.isPresent()) {
            return optionalAdmin.get();
        }
        throw new RuntimeException("Ошибка при конвертации объекта Admin");
    }
}
