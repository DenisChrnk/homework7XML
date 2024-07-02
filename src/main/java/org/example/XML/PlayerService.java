package org.example.XML;


import jakarta.xml.bind.JAXBException;

import java.util.Collection;

public interface PlayerService {

    // получить игрока по id
    Player getPlayerById(int id) throws JAXBException;

    // получить список игроков
    Collection<Player> getPlayers() throws JAXBException;

    // создать игрока (возвращает id нового игрока)
    int createPlayer(String nickname) throws JAXBException;

    // удалить игрока по id'шнику, вернет удаленного игрока
    Player deletePlayer(int id) throws JAXBException;

    // добавить очков игроку. Возвращает обновленный счет
    int addPoints(int playerId, int points) throws JAXBException;
}
