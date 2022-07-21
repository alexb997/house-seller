//package com.example.demo.services;
//
//import com.example.demo.models.Characteristics;
//import com.example.demo.models.House;
//import com.example.demo.repository.HouseRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.*;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(SpringRunner.class)
//public class HouseServiceTest {
//
//    @Mock
//    private HouseRepository houseRepository;
//
//    @InjectMocks
//    private HouseService houseService;
//
//    House mockHouse = new House(121,"Fully-mobilated","300x600x900","Somewhere","Ms.Nobody",1200,10,"SomeDescription",new Characteristics());
//
//    @Test
//    public void findByIdTest(){
//        Mockito.when(houseRepository.findById(Mockito.anyString())).thenReturn(Optional.of(mockHouse));
//
//        House result = houseService.getByID("SomeID");
//        System.out.println(result.toString());
//        assertThat(result).isNotNull();
//    }
//
//    @Test
//    public void addHouseTest(){
//        Mockito.when(houseRepository.save(Mockito.any(House.class))).thenReturn(mockHouse);
//
//        House result = houseService.addHouse(new House());
//        String expected ="House{id='null', number=121, status='Fully-mobilated', dimensions='300x600x900', address='Somewhere', owner='Ms.Nobody', price=1200, reduction=10, description='SomeDescription', characteristics={}";
//
//        assertThat(result.toString()).isEqualTo(expected);
//        System.out.println(result);
//    }
//
//    @Test
//    public void editHouseTest(){
//
//        Mockito.when(houseRepository.findById(Mockito.anyString())).thenReturn(java.util.Optional.of(mockHouse));
//        Mockito.when(houseRepository.save(Mockito.any(House.class))).thenReturn(mockHouse);
//        mockHouse.setOwner("Someone");
//        Optional<House> result = houseService.editHouse(Mockito.anyString(),new House());
//        String expected ="Optional[House{id='null', number=121, status='Fully-mobilated', dimensions='300x600x900', address='Somewhere', owner='Someone', price=1200, reduction=10, description='SomeDescription'}]";
//
//        assertThat(result.toString()).isEqualTo(expected);
//        System.out.println(result);
//        mockHouse.setOwner("Ms.Nobody");
//    }
//
//    @Test
//    public void allHousesTest() throws Exception{
//        List<House> mockHouseList= new ArrayList<>();
//        House mockHouse2 = new House(122,"Fully-mobilated","300x600x900","Somewhere-else","Mr.Nobody",1200,10,"SomeDescription",new Characteristics());
//        House mockHouse3 = new House(123,"Fully-mobilated","300x600x900","Somewhere","Ms.Nobody",1200, 10,"SomeDescription",new Characteristics());
//
//        mockHouseList.add(mockHouse);
//        mockHouseList.add(mockHouse2);
//        mockHouseList.add(mockHouse3);
//        Page<House> mockPageHouses= new PageImpl<>(mockHouseList);
//        Mockito.when(houseRepository.findAll(Mockito.any(Pageable.class))).thenReturn(mockPageHouses);
//        Page<House> result = houseService.findAll(PageRequest.of(0,3));
//
//        assertThat(result.getTotalElements()).isEqualTo(mockPageHouses.getTotalElements());
//        System.out.println(result.getTotalElements());
//    }
//
//    @Test
//    public void findByFiltersTest() {
//        List<House> mockHouseList= new ArrayList<>();
//        List<House> mockHouseList2= new ArrayList<>();
//        House mockHouse2 = new House(122,"Fully-mobilated","300x600x900","Somewhere-else","Mr.Nobody",1200,10,"SomeDescription",new Characteristics());
//        House mockHouse3 = new House(123,"Fully-mobilated","300x600x900","Somewhere","Ms.Nobody",1200, 10,"SomeDescription",new Characteristics());
//        House mockHouse4 = new House(125,"No furnishing","300x600x900","Somewhere","Mr.Nobody",1200, 10,"SomeDescription",new Characteristics());
//
//        mockHouseList.add(mockHouse);
//        mockHouseList.add(mockHouse2);
//        mockHouseList.add(mockHouse3);
//        mockHouseList.add(mockHouse4);
//
//        mockHouseList2.add(mockHouse2);
//        mockHouseList2.add(mockHouse3);
//
//        Map<String,String> mockFilters= new HashMap<>();
//
//        Page<House> mockPageHouses= new PageImpl<>(mockHouseList);
//        Mockito.when(houseRepository.findAllByStatusMatchesRegexAndDimensionsMatchesRegexAndAddressMatchesRegexAndOwnerMatchesRegex(Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.anyString(),Mockito.any(Pageable.class))).thenReturn(mockPageHouses);
//        Page<House> result = houseService.findByFilters(mockFilters,PageRequest.of(0,3));
//
//        assertThat(result.getTotalElements()).isEqualTo(mockPageHouses.getTotalElements());
//        System.out.println(result.getTotalElements());
//    }
//
//    @Test
//    public void removeAllTest(){
//        houseService.removeAllHouses();
//        Mockito.verify(houseRepository).deleteAll();
//    }
//
//
//    @Test
//    public void removeByIDTest(){
//        //
////        String mockId = "someID";
////        houseService.removeSpecificHouse(mockId);
////        Mockito.verify(houseRepository).deleteById(mockId);
//    }
//};
