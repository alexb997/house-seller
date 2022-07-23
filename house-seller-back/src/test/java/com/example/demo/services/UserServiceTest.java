//package com.example.demo.services;
//
//import com.example.demo.models.User;
//import com.example.demo.repository.UserRepository;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.junit.Test;
//import org.mockito.Mockito;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(SpringRunner.class)
//public class UserServiceTest {
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserService userService;
//
//    private User mockUser = new User("Mr.Nobody","pass1234");
//
//    @Test
//    public void addNewUserTest() throws Exception{
//        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(mockUser);
//
//        User result = userService.addNewUser(new User());
//        String expected ="User{id='null', username='Mr.Nobody', password='pass1234}";
//
//        assertThat(result.toString()).isEqualTo(expected);
//        System.out.println(result);
//    }
//
//    @Test
//    public void getAllUsersTest() throws Exception{
//        User mockUser2 = new User("Mr.Nobody2","pass4321");
//        User mockUser3 = new User("Mr.Nobody3","pass42");
//        List<User> mockUsers= new ArrayList<>();
//        mockUsers.add(mockUser);
//        mockUsers.add(mockUser2);
//        mockUsers.add(mockUser3);
//        Page<User> pageUsers= new PageImpl<>(mockUsers);
//        Mockito.when(userRepository.findAll(Mockito.any(Pageable.class))).thenReturn(pageUsers);
//        Page<User> result = userService.allUsers(PageRequest.of(0,3));
//
//        assertThat(result.getTotalElements()).isEqualTo(pageUsers.getTotalElements());
//        System.out.println(result.getTotalElements());
//    }
//
////    @Test
////    public void removeByIDTest(){
////    }
//
//}