package com.chakray.testmid.service;

import com.chakray.testmid.model.AddressModel;
import com.chakray.testmid.model.UsersModel;
import com.chakray.testmid.repository.UsersRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author luis-barrera
 */
@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    //Save user
    public UsersModel saveUser(UsersModel user) {

        if (usersRepository.existsByTaxId(user.getTaxId())) {
            throw new RuntimeException("Ya existe este tax_id");
        }

        //COmo poner una zona especifica parta Madagascar
        ZoneId zone = ZoneId.of("Indian/Antananarivo");
        LocalDateTime now = ZonedDateTime.now(zone).toLocalDateTime();
        user.setCreatedAt(now);

        for (AddressModel address : user.getAddresses()) {
            address.setUser(user);
        }

        return usersRepository.save(user);
    }

    //GET sortedBy
    public List<UsersModel> getAllUsers(String sortedBy) {

        if (sortedBy == null) {
            return usersRepository.findAll();
        }

        List<String> ordenar = List.of("email", "id", "name", "phone", "taxId", "createdAt");

        if (!ordenar.contains(sortedBy)) {
            throw new RuntimeException("Formato de ordnado invalido.");
        }

        return usersRepository.findAll(Sort.by(sortedBy));
    }

    //Update user
    public UsersModel updateUser(Integer id, UsersModel user) {

        Optional<UsersModel> existeUser = usersRepository.findById(id);

        if (existeUser.isEmpty()) {
            throw new RuntimeException("No existe usuario con este ID.");
        }

        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            existeUser.get().setEmail(user.getEmail());
        }
        if (user.getName() != null && !user.getName().isEmpty()) {
            existeUser.get().setName(user.getName());
        }
        if (user.getPhone() != null && !user.getPhone().isEmpty()) {
            existeUser.get().setPhone(user.getPhone());
        }
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existeUser.get().setPassword(user.getPassword());
        }
        if (user.getTaxId() != null && !user.getTaxId().isEmpty()
                && !user.getTaxId().equals(existeUser.get().getTaxId())
                && usersRepository.existsByTaxId(user.getTaxId())) {
            throw new RuntimeException("TaxId ya existe.");
        }
        if (user.getTaxId() != null) {
            existeUser.get().setTaxId(user.getTaxId());
        }

        return usersRepository.save(existeUser.get());
    }

    public void deleteUser(Integer id) {
        if (!usersRepository.existsById(id)) {
            throw new RuntimeException("No existe registro con este ID.");
        }
        usersRepository.deleteById(id);
    }

    public List<UsersModel> filterUsers(String filter) {
        if (filter == null || filter.isEmpty()) {
            throw new RuntimeException("El filter no puede estar vacio.");
        }
        System.out.println("filter " + filter);
//        String[] parts = filter.split("\\+");
        String[] parts = filter.split(" ");
        if (parts.length != 3) {
            throw new RuntimeException("Estructra incorrecyta favor de verificar.");
        }

        String clave = parts[0];
        String casoUso = parts[1];
        String value = parts[2];

        System.out.println("clave " + clave);
        System.out.println("casoUso " + casoUso);
        System.out.println("value " + value);

        List<String> listaClaves = List.of(
                "email", "id", "name", "phone", "taxId", "createdAt"
        );

        if (!listaClaves.contains(clave)) {
            throw new RuntimeException("Clave invalida");
        }

        //con JPA nos ayuda hacer como si fuera nuestro where
        Specification<UsersModel> spec = (root, query, cb) -> {

            switch (casoUso) {

                case "eq":
                    return cb.equal(root.get(clave), value);

                case "co":
                    return cb.like(cb.lower(root.get(clave)), "%" + value.toLowerCase() + "%");

                case "sw":
                    return cb.like(cb.lower(root.get(clave)), value.toLowerCase() + "%");

                case "ew":
                    return cb.like(cb.lower(root.get(clave)), "%" + value.toLowerCase());

                default:
                    throw new RuntimeException("Invalid operator");
            }
        };

        return usersRepository.findAll(spec);
    }

    public UsersModel login(String taxId, String pass) {

        Optional<UsersModel> user = usersRepository.findByTaxId(taxId);

        if (!user.get().getPassword().equals(pass)) {
            throw new RuntimeException("Usario y/o contraseña incorrectas, favor de validar.");
        }

        return user.get();
    }

}
