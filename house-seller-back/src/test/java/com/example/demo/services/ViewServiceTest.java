package com.example.demo.services;

import com.example.demo.models.View;
import com.example.demo.repository.ViewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ViewServiceTest {
    @Mock
    private ViewRepository viewRepository;

    @InjectMocks
    private ViewService viewService;

    private View mockView = new View("userID", "houseID", new Date());
    private String dateString = new Date().toString();

    @Test
    public void addNewViewTest() throws Exception {
        Mockito.when(viewRepository.save(Mockito.any(View.class))).thenReturn(mockView);

        View result = viewService.addNewView(new View());
        String expected = "View{id='null', userID='userID', houseID='houseID', viewDate="+dateString+"}";

        assertThat(result.toString()).isEqualTo(expected);
        System.out.println(result);
    }

    @Test
    public void getAllViewsTest() throws Exception {
        View mockView2 = new View("userID2", "houseID3", new Date());
        View mockView3 = new View("userID3", "houseID2", new Date());
        List<View> mockViews = new ArrayList<>();
        mockViews.add(mockView);
        mockViews.add(mockView2);
        mockViews.add(mockView3);
        Page<View> pageViews = new PageImpl<>(mockViews);
        Mockito.when(viewRepository.findAll(Mockito.any(Pageable.class))).thenReturn(pageViews);
        Page<View> result = viewService.allViews(PageRequest.of(0, 3));

        assertThat(result.getTotalElements()).isEqualTo(pageViews.getTotalElements());
        System.out.println(result.getTotalElements());
    }

    @Test
    public void allByUserTest() throws Exception {
        View mockView2 = new View("userID", "houseID3", new Date());
        View mockView3 = new View("userID", "houseID2", new Date());
        List<View> mockViews = new ArrayList<>();
        mockViews.add(mockView);
        mockViews.add(mockView2);
        mockViews.add(mockView3);
        Page<View> pageViews = new PageImpl<>(mockViews);
        Mockito.when(viewRepository.findAllByUserID(Mockito.anyString(),Mockito.any(Pageable.class))).thenReturn(pageViews);
        Page<View> result = viewService.allByUser("userID",PageRequest.of(0, 3));

        assertThat(result.getTotalElements()).isEqualTo(pageViews.getTotalElements());
    }

    @Test
    public void allByHouseTest() throws Exception {
        View mockView2 = new View("userID2", "houseID", new Date());
        View mockView3 = new View("userID3", "houseID", new Date());
        List<View> mockViews = new ArrayList<>();
        mockViews.add(mockView);
        mockViews.add(mockView2);
        mockViews.add(mockView3);
        Page<View> pageViews = new PageImpl<>(mockViews);
        Mockito.when(viewRepository.findAllByHouseID(Mockito.anyString(),Mockito.any(Pageable.class))).thenReturn(pageViews);
        Page<View> result = viewService.allByHouse("houseID",PageRequest.of(0, 3));

        assertThat(result.getTotalElements()).isEqualTo(pageViews.getTotalElements());
    }
}
