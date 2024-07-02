package org.example.XML;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class PlayerServiceXML implements PlayerService {

    @Override
    public Player getPlayerById(int id) throws JAXBException {
        List<Player> allPlayers = getPlayers().stream().toList();
        for (Player pl : allPlayers) {
            if (pl.getId() == id)
                return pl;
        }
        return null;
    }

    @Override
    public Collection<Player> getPlayers() throws JAXBException {
        Path filePath = Path.of("player.xml");
        JAXBContext context = JAXBContext.newInstance(Players.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Players getPl = (Players) unmarshaller.unmarshal(filePath.toFile());
        Collection<Player> allPlayers = getPl.getPlayerList();

        return allPlayers;
    }

    @Override
    public int createPlayer(String nickname) throws JAXBException {
        Path filePath = Path.of("player.xml");
        JAXBContext context = JAXBContext.newInstance(Players.class);
        Collection<Player> listPlayer = getPlayers();

        if (listPlayer.size() - 1 == -1) {
            int i = 1;
            List<Player> allPl = new ArrayList<>();
            allPl.addAll(listPlayer);
            Player players = new Player(i, nickname, 0, true);
            allPl.add(players);
            Players playerToList = new Players(allPl);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(playerToList, filePath.toFile());

            return players.getId();
        }
        int a = listPlayer.size() - 1;
        Player b = listPlayer.stream().toList().get(a);
        int i = b.getId() + 1;
        List<Player> allPl = new ArrayList<>();
        allPl.addAll(listPlayer);
        Player players = new Player(i, nickname, 0, true);
        allPl.add(players);
        Players playerToList = new Players(allPl);
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(playerToList, filePath.toFile());

        return players.getId();
    }

    @Override
    public Player deletePlayer(int id) throws JAXBException {
        Path filePath = Path.of("player.xml");
        JAXBContext context = JAXBContext.newInstance(Players.class);
        Collection<Player> allPlayerForDel = getPlayers();
        Player playerForDel = null;
        for (Player delPlayer : allPlayerForDel) {
            if (delPlayer.getId() == id) {
                playerForDel = delPlayer;
            }
        }
        allPlayerForDel.remove(playerForDel);
        Players playerToList = new Players((List<Player>) allPlayerForDel);
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(playerToList, filePath.toFile());
        return playerForDel;
    }

    @Override
    public int addPoints(int playerId, int points) throws JAXBException {
        Path filePath = Path.of("player.xml");
        JAXBContext context = JAXBContext.newInstance(Players.class);
        Collection<Player> allPlayerAddPoints = getPlayers();
        Player addPointPlayer = null;
        for (Player playerForAdd : allPlayerAddPoints) {
            if (playerForAdd.getId() == playerId) {
                addPointPlayer = playerForAdd;
            }
        }
        addPointPlayer.setPoints(points);
        Players playerWithNewPoint = new Players((List<Player>) allPlayerAddPoints);
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(playerWithNewPoint, filePath.toFile());
        return addPointPlayer.getPoints();
    }
}
