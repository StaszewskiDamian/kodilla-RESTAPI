package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTest {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void shouldMapToBoards() {
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("1", "Test List", false));
        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        trelloBoardsDto.add(new TrelloBoardDto("666", "trelloBoard", trelloListsDto));
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);
        //Then
        assertEquals("666", trelloBoards.get(0).getId());
        assertEquals("trelloBoard", trelloBoards.get(0).getName());
        assertEquals("Test List", trelloBoards.get(0).getLists().get(0).getName());
        assertEquals("1", trelloBoards.get(0).getLists().get(0).getId());
    }

    @Test
    public void shouldMapToBoardsDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "Test List", false));
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("666", "trelloBoard", trelloLists));
        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals("666", trelloBoardsDto.get(0).getId());
        assertEquals("trelloBoard", trelloBoardsDto.get(0).getName());
        assertEquals("Test List", trelloBoardsDto.get(0).getLists().get(0).getName());
        assertEquals("1", trelloBoardsDto.get(0).getLists().get(0).getId());
    }

    @Test
    public void shouldMapToList() {
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("1", "Test List", false));
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);
        //Then
        assertEquals("Test List", trelloLists.get(0).getName());
        assertEquals("1", trelloLists.get(0).getId());
    }

    @Test
    public void shouldMapToListDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "Test List", false));
        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);
        //Then
        assertEquals("Test List", trelloListDtos.get(0).getName());
        assertEquals("1", trelloListDtos.get(0).getId());
    }

    @Test
    public void shouldMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard(
                "Card", "card descripion",
                "card pos", "card listId");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("Card", trelloCardDto.getName());
        assertEquals("card descripion", trelloCardDto.getDescription());
        assertEquals("card pos", trelloCardDto.getPos());
        assertEquals("card listId", trelloCardDto.getListId());
    }

    @Test
    public void shouldMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Card", "card descripion",
                "card pos", "card listId");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("Card", trelloCard.getName());
        assertEquals("card descripion", trelloCard.getDescription());
        assertEquals("card pos", trelloCard.getPos());
        assertEquals("card listId", trelloCard.getListId());
    }
}
