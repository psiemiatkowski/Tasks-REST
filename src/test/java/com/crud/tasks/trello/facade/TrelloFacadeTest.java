package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTest {

    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void shouldFetchTrelloBoards(){
        //Given
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(new TrelloListDto("TestID25", "TestList3", false));

        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(new TrelloBoardDto("TestID10", "TestBoard4", trelloListDtoList));

        List<TrelloList> trelloListList = new ArrayList<>();
        trelloListList.add(new TrelloList("TestID14", "TestList6", false));

        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(new TrelloBoard("TestID17", "TestBoard2", trelloListList));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtoList);
        when(trelloMapper.mapToBoards(trelloBoardDtoList)).thenReturn(trelloBoardList);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoardDtoList);
        when(trelloValidator.validateTrelloBoards(trelloBoardList)).thenReturn(trelloBoardList);

        //When
        List<TrelloBoardDto> fetchTrelloBoards = trelloFacade.fetchTrelloBoards();

        //Then
        Assert.assertNotNull(fetchTrelloBoards);
        Assert.assertEquals(1, fetchTrelloBoards.size());

        fetchTrelloBoards.forEach(trelloBoardDto -> {
            assertEquals("TestID10", trelloBoardDto.getId());
            assertEquals("TestBoard4", trelloBoardDto.getName());

            trelloBoardDto.getLists().forEach(trelloListDto -> {
                assertEquals("TestID25", trelloListDto.getId());
                assertEquals("TestList3", trelloListDto.getName());
                assertEquals(false, trelloListDto.isClosed());
            });
        });
    }

    @Test
    public void  shouldFetchEmptyList()  {
        //Given
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(new TrelloListDto("TestID25", "TestList3", false));

        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(new TrelloBoardDto("TestID10", "TestBoard4", trelloListDtoList));

        List<TrelloList> trelloListList = new ArrayList<>();
        trelloListList.add(new TrelloList("TestID14", "TestList6", false));

        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(new TrelloBoard("TestID17", "TestBoard2", trelloListList));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoardDtoList);
        when(trelloMapper.mapToBoards(trelloBoardDtoList)).thenReturn(trelloBoardList);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(new ArrayList<>());
        when(trelloValidator.validateTrelloBoards(trelloBoardList)).thenReturn(new ArrayList<>());

        //When
        List<TrelloBoardDto> fetchTrelloBoards = trelloFacade.fetchTrelloBoards();

        //Then
        Assert.assertNotNull(fetchTrelloBoards);
        Assert.assertEquals(0, fetchTrelloBoards.size());
    }
}
