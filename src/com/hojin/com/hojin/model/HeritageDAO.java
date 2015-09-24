package com.hojin.com.hojin.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by hojin on 15. 9. 23.
 */
public class HeritageDAO {
    String authKey="757164717163686a363145416a7a46";
    String requestUrl=null;
    ArrayList<HeritageVO> arrayList;

    public ArrayList<HeritageVO> receiveData(int beginNum,int endNum){
        try{
            requestUrl="http://openapi.seoul.go.kr:8088/"+authKey+"/xml/ListCulturalAssetsInfo/"+beginNum+"/"+endNum;
            System.out.println("요청 사이트 주소: requestUrl="+requestUrl);

            URL url=new URL(requestUrl);
            InputStream inputStream=url.openStream();

            DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
            Document document=documentBuilder.parse(inputStream);       //파싱.
            document.getDocumentElement().normalize();

            System.out.println("Root element :" + document.getDocumentElement().getNodeName());

            NodeList nodeList=document.getElementsByTagName("row");         //heritageVO의 각 엘리먼트(필드)들
            System.out.println("-------------------------------------------------");

            arrayList=new ArrayList<HeritageVO>();      //HeritageVO를 arrayList에 저장해서 리턴하기 위한 용도임.
            for(int temp=0;temp<nodeList.getLength();temp++){
                Node node=nodeList.item(temp);                              //각 엘리먼트들을 Node에 저장함.

                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element element=(Element)node;

                    String heritageId=getTagValue("MANAGE_NUM",element);
                    String heritageName=getTagValue("NAME_KOR",element);
                    String heritageSize=getTagValue("SCALE",element);

                    //위 값으로 매 VO생성
                    HeritageVO heritageVO=new HeritageVO(heritageId,heritageName,heritageSize);
                    arrayList.add(heritageVO);      //방금 초기화된 VO를 arrayList에 추가함.

                }
            }

        }catch(Exception exception){
            exception.printStackTrace();
        }
        return arrayList;
    }

    private static String getTagValue(String sTag, Element eElement){
        NodeList nodeList=eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node node=nodeList.item(0);
        return node.getNodeValue();
    }

    public HeritageVO receiveDetailData(String heritageId){
        requestUrl="http://openapi.seoul.go.kr:8088/"+authKey+"/xml/ListCulturalAssetsInfo/1/5/"+heritageId;
        HeritageVO heritageVO=null;
        try{
            URL url=new URL(requestUrl);
            InputStream inputStream=url.openStream();

            DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
            Document document=documentBuilder.parse(inputStream);     //파싱
            document.getDocumentElement().normalize();

            System.out.println("Root element : " + document.getDocumentElement().getNodeName());
            NodeList nodeList=document.getElementsByTagName("row");
            System.out.println("---------------------------------------------------");

            for(int temp=0;temp<nodeList.getLength();temp++){
                Node node=nodeList.item(temp);
                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element element=(Element)node;

                    String heriId=getTagValue("MANAGE_NUM",element);
                    String heritageName=getTagValue("NAME_KOR",element);
                    String heritageSize=getTagValue("SCALE",element);
                    String heritageInfo=getTagValue("BOARD_KOR",element);

                    heritageVO=new HeritageVO(heriId,heritageName,heritageSize,heritageInfo);
                }
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }
        return  heritageVO;
    }
}



























