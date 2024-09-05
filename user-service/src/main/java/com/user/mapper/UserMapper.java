package com.user.mapper;

import com.user.dto.AdminDTO;
import com.user.dto.CustomerDTO;
import com.user.model.Admin;
import com.user.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {


    AdminDTO toAdminDTO(Admin admin);

    Admin toAdminEntity(AdminDTO dto);


    CustomerDTO toCustomerDTO(Customer customer);

    Customer toCustomerEntity(CustomerDTO dto);
}
