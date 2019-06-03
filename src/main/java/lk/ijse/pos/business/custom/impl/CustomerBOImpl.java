package lk.ijse.pos.business.custom.impl;

import lk.ijse.pos.business.custom.CustomerBO;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class CustomerBOImpl implements CustomerBO {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public List<CustomerDTO> getAllCustomers() throws Exception {
            List<CustomerDTO> customerDTOStream = customerDAO.findAll().stream().map(customer -> new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress())).collect(Collectors.toList());
            return customerDTOStream;
    }

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws Exception {

            customerDAO.save(new Customer(dto.getId(),dto.getName(),dto.getAddress()));
            return true;

    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws Exception {
        customerDAO.update(new Customer(dto.getId(),dto.getName(),dto.getAddress()));

            return true;

    }

    @Override
    public boolean removeCustomer(String id) throws Exception {

                customerDAO.delete(id);

                return true;

    }

    @Override
    public CustomerDTO getCustomerById(String id) throws Exception {

            Customer customer = customerDAO.find(id);
            CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress());
            return customerDTO;
        }
    }

//    public CustomerBOImpl(){
//        String lk.ijse.pos.dao = DAOFactory.getInstance().<String>getDAO(DAOTypes.CUSTOMER);
//    }

    //    public CustomerBOImpl(){
//        String lk.ijse.pos.dao = DAOFactory.getInstance().<String>getDAO(DAOTypes.CUSTOMER);
//    }



