package com.example.picolotest;

import android.content.Context;
import android.content.res.AssetManager;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



import java.io.IOException;
import java.io.InputStream;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Lecture {

    private NodeList nodeListRequete;
    private NodeList nodeListUtilisateur;
    private AssetManager fichiers;


    public Lecture(AssetManager asset) {
        fichiers = asset;

        InputStream fichier;
        Document document = null;
        try {
            fichier = asset.open("listequestionbalanced.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                try {
                    document = dBuilder.parse(fichier);
                    document.getDocumentElement().normalize();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        nodeListRequete = document.getElementsByTagName("Question");
    }


    public String[][] getURL() {
        String[][] liste = null;
        Node node = null;
        Element element = null;
        int n = nodeListRequete.getLength();

        if (n != 0) {
            liste = new String[n][3];
            for (int i = 0; i < n; i++) {
                node = nodeListRequete.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    element = (Element) node;
                    liste[i][0] = element.getElementsByTagName("phrase").item(0).getTextContent();
                    liste[i][0] = liste[i][0].replace("\n", "");
                    liste[i][1] = element.getElementsByTagName("Type").item(0).getTextContent();
                    liste[i][1] = liste[i][1].replace("\n", "");
                    liste[i][2] = element.getElementsByTagName("Nombre").item(0).getTextContent();
                    liste[i][2] = liste[i][2].replace("\n", "");

                }
            }
        }

        return liste;
    }
}