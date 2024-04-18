package com.g4l.timesheet_backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.g4l.timesheet_backend.models.entities.Consultant;
import com.g4l.timesheet_backend.models.requests.UserRequest;
import com.g4l.timesheet_backend.models.responses.ConsultantResponse;
import com.g4l.timesheet_backend.repositories.ConsultantRepository;
import com.g4l.timesheet_backend.services.implementations.ConsultantServiceImpl;
import com.g4l.timesheet_backend.services.interfaces.UserService;
import com.g4l.timesheet_backend.utils.exceptions.user.UserDetailsAlreadyExistsException;
import com.g4l.timesheet_backend.utils.exceptions.user.UserDetailsNotFoundException;
import com.g4l.timesheet_backend.utils.mappers.models.UserMapper;

@ExtendWith(MockitoExtension.class)
public class ConsultantServiceTest {
        @Mock
        private ConsultantRepository consultantRepository;
        @Mock
        private UserService userService;
        @Mock
        private UserMapper userMapper;

        @InjectMocks
        private ConsultantServiceImpl consultantService;

        Consultant con1 = new Consultant("id1", "1234567890001", "John", "Doe", "john_doe", "johdoe@email.com",
                        "1234567890", "team1");
        Consultant con2 = new Consultant("id2", "1234567890002", "Jane", "Doe", "jane_doe", "janedoe@email.com",
                        "1234567891", "team2");
        List<Consultant> consultants = List.of(con1, con2);

        @Test
        void testCreateConsultant_Success() {
                UserRequest userRequest = new UserRequest("1234567890001", "Jake", "Doe", "jake_doe",
                                "jakedoe@mail.com",
                                "1234567892");
                Consultant newCon = new Consultant();
                newCon.setId("12345");
                newCon.setIdNumber(userRequest.getIdNumber());
                newCon.setFirstName(userRequest.getFirstName());
                newCon.setLastName(userRequest.getLastName());
                newCon.setUserName(userRequest.getUserName());
                newCon.setEmail(userRequest.getEmail());
                newCon.setPhoneNumber(userRequest.getPhoneNumber());

                when(userService.doesUserExist(userRequest.getUserName(), userRequest.getIdNumber(),
                                userRequest.getEmail()))
                                .thenReturn(false);
                when(userMapper.userRequestToConsultant(userRequest)).thenReturn(newCon);
                when(userService.createUser(newCon)).thenReturn(newCon);
                when(consultantRepository.save(newCon)).thenReturn(newCon);

                Object result = consultantService.createConsultant(userRequest);

                assertNotNull(result);
                assertTrue(result instanceof Consultant);
        }

        @Test
        void testCreateConsultant_Failure() {
                UserRequest userRequest = new UserRequest();
                userRequest.setEmail("email@email.com");
                userRequest.setIdNumber("1234567890001");
                userRequest.setUserName("jake_doe");

                assertThrows(UserDetailsAlreadyExistsException.class, () -> {
                        throw new UserDetailsAlreadyExistsException(userRequest.getUserName(),
                                        userRequest.getIdNumber(),
                                        userRequest.getEmail());
                });
        }

        @Test
        void testUpdateConsultant_Faiure() {
                UserRequest userRequest = new UserRequest();
                userRequest.setEmail("dummy@email.com");
                userRequest.setIdNumber("1234567890009");
                userRequest.setUserName("jakes_don");

                assertThrows(UserDetailsNotFoundException.class, () -> {
                        throw new UserDetailsNotFoundException(userRequest.getUserName(), userRequest.getIdNumber(),
                                        userRequest.getEmail());
                });
        }

        @Test
        void testUpdateConsultant_Success() {
                UserRequest userRequest = new UserRequest("1234567890001", "Jake", "Doe", "jake_doe",
                                "jakedoe@mail.com",
                                "1234567892");
                Consultant oldCon = new Consultant();
                oldCon.setId("12345");
                oldCon.setIdNumber(userRequest.getIdNumber());
                oldCon.setUserName(userRequest.getUserName());
                oldCon.setEmail(userRequest.getEmail());
                oldCon.setPhoneNumber(userRequest.getPhoneNumber());

                Consultant newCon = oldCon;

                when(userService.getUser(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                                .thenReturn(oldCon);
                when(userService.updateUserDetails(Mockito.any(Consultant.class), Mockito.any(UserRequest.class)))
                                .thenReturn(newCon);
                when(consultantRepository.save(Mockito.any(Consultant.class))).thenReturn(newCon);

                Object result = consultantService.updateConsultant(userRequest);

                assertEquals(newCon, result);
        }

        @Test
        void testGetConsultantById_Success() {
                when(consultantRepository.findById(Mockito.anyString())).thenReturn(Optional.of(con1));

                Object result = consultantService.getConsultantById("id1");

                assertNotNull(result);
        }

        @Test
        void testDeleteConsultant_Seccess() {
                when(userService.doesUserExist(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(true);

                Object result = consultantService.deleteConsultant("id1");

                assertEquals("Consultant with consultant id [id1] deleted", result);
        }

        @Test
        void testGetAllConsultants_Success() {
                when(consultantRepository.findAll()).thenReturn(consultants);

                List<ConsultantResponse> result = consultantService.getAllConsultants();

                assertTrue(result.size() > 0);
        }

        @Test
        void testAssignConsultantToTeam_Success() {
                when(consultantRepository.findById(Mockito.anyString())).thenReturn(Optional.of(con1));
                when(consultantRepository.save(Mockito.any(Consultant.class))).thenReturn(con1);

                Object result = consultantService.assignConsultantToClientTeam(con1.getId(), "team1");

                assertTrue(result instanceof Consultant);
                assertNotNull(result);
        }

        @Test
        void testGetConsultant_Success() {
                when(userService.getUser(Mockito.anyString())).thenReturn(con1);

                Consultant result = consultantService.getConsultant("john_doe");

                assertNotNull(result);
        }

}
