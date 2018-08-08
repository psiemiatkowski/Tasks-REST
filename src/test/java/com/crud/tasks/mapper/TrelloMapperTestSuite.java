package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() throws Exception {
        //Given
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("DTOBoardID1", "DTOBoardName1", new ArrayList<>());
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("DTOBoardID2", "DTOBoardName2", new ArrayList<>());
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto1);
        trelloBoardDtoList.add(trelloBoardDto2);
        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);
        //Then
        assertEquals(2, trelloBoardList.size());
        assertEquals("DTOBoardID1", trelloBoardList.get(0).getId());
        assertEquals("DTOBoardName2", trelloBoardList.get(1).getName());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        TrelloBoard trelloBoard = new TrelloBoard("BoardID1", "BoardName1", new ArrayList<>());
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);
        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals(1, trelloBoardDtoList.size());
        assertEquals("BoardID1", trelloBoardDtoList.get(0).getId());
        assertEquals("BoardName1", trelloBoardDtoList.get(0).getName());
    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("DTOListID1", "DTOListName1", true);
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(trelloListDto);
        //When
        List<TrelloList> trelloListList = trelloMapper.mapToList(trelloListDtoList);
        //Then
        assertEquals(1, trelloListList.size());
        assertEquals("DTOListID1", trelloListList.get(0).getId());
        assertEquals("DTOListName1", trelloListList.get(0).getName());
        assertTrue(trelloListList.get(0).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        TrelloList trelloList1 = new TrelloList("ListID1", "ListName1", true);
        TrelloList trelloList2 = new TrelloList("ListID2", "ListName2", false);
        List<TrelloList> trelloListList = new ArrayList<>();
        trelloListList.add(trelloList1);
        trelloListList.add(trelloList2);
        //When
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(trelloListList);
        //Then
        assertEquals(2, trelloListDtoList.size());
        assertEquals("ListID1", trelloListDtoList.get(0).getId());
        assertEquals("ListName2", trelloListDtoList.get(1).getName());
        assertFalse(trelloListDtoList.get(1).isClosed());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("CardID1", "CardDescription1",
                "CardPos1", "CardListID1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("CardID1", trelloCardDto.getName());
        assertEquals("CardDescription1", trelloCardDto.getDescription());
        assertEquals("CardPos1", trelloCardDto.getPos());
        assertEquals("CardListID1", trelloCardDto.getListId());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("DTOCardID1", "DTOCardDescription1",
                "DTOCardPos1", "DTOCardListID1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("DTOCardID1", trelloCard.getName());
        assertEquals("DTOCardDescription1", trelloCard.getDescription());
        assertEquals("DTOCardPos1", trelloCard.getPos());
        assertEquals("DTOCardListID1", trelloCard.getListId());
    }
}
