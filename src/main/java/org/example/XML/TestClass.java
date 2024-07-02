package org.example.XML;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TestClass {
    public static void main(String[] args) throws JAXBException {
        PlayerService service = new PlayerServiceXML();


//        System.out.println(service.createPlayer("player8"));
//        System.out.println(service.createPlayer("player6"));
//        System.out.println(service.getPlayers());
//        System.out.println(service.getPlayerById(2));
//        System.out.println(service.deletePlayer(3));
//        System.out.println(service.addPoints(2, 11));
    }
}
