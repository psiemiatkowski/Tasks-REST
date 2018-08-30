package com.crud.tasks.service;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Test
    public void shouldCreateNewCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("TrelloCardDtoName", "TrelloCardDtoDescription",
                "TrelloCardDtoPos", "1");

        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "CreatedTrelloCardDtoName",
                "CreatedTrelloCardDtoShortUrl", null);

        when(trelloService.createdTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        //When
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);

        //Then
        Assert.assertEquals("1", newCard.getId());
        Assert.assertEquals("CreatedTrelloCardDtoName", newCard.getName());
        Assert.assertEquals("CreatedTrelloCardDtoShortUrl", newCard.getShortUrl());
    }

    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        trelloListDto.add(new TrelloListDto("1", "TrelloListDtoName", true));

        List<TrelloBoardDto> trelloBoardDtoArrayList = new ArrayList<>();
        trelloBoardDtoArrayList.add(new TrelloBoardDto("1", "TrelloBoardDtoName", trelloListDto));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoArrayList);

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloService.fetchTrelloBoards();

        //Then
        Assert.assertNotNull(trelloBoardDtoList);
        Assert.assertEquals(1, trelloBoardDtoList.size());
    }
}
