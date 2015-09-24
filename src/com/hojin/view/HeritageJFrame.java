package com.hojin.view;

import com.hojin.Controller.HeritageController;
import com.hojin.com.hojin.model.HeritageModel;
import com.hojin.com.hojin.model.HeritageVO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by hojin on 15. 9. 23.
 */
public class HeritageJFrame extends JFrame implements ActionListener{
    JLabel jLabel;
    JTable jTable;

    Panel panel;
    JButton btnNext,btnPrevious;
    String[][] heritageItems;

    HeritageController heritageController;

    int heritageNumPerPage=40;
    int pageNum=0;
    int beginNum=heritageNumPerPage*pageNum+1;
    int endNum=heritageNumPerPage*(pageNum+1);


    ArrayList<HeritageVO> arrayList;

    HeritageModel heritageModel;
    int rowIndex=-1;

    public HeritageJFrame(){
        super("문화재 정보 조회 화면");
        jLabel=new JLabel("문화재 정보 조회 결과");
        jTable=new JTable();

        panel=new Panel();
        btnNext=new JButton("다음");
        btnPrevious=new JButton("이전");
        heritageItems=new String[0][2];

        //컨트롤러 생성 : 요청사항을 해당 컨트롤러에게 토스 역할.
        init();
    }

    private void init(){
        heritageController=new HeritageController();
        arrayList=heritageController.receiveData(beginNum,endNum);
        loadTableData();
        startJFrame();
    }

    private void loadTableData(){
        heritageItems=new String[arrayList.size()][2];
        for(int i=0;i<arrayList.size();i++){
            HeritageVO heritageVO=arrayList.get(i);
            heritageItems[i][0]=heritageVO.getHeritageId();
            heritageItems[i][1]=heritageVO.getHeritageTitle();
        }

        //데이터 모델 생성.
        heritageModel=new HeritageModel(heritageItems);
        jTable.setModel(heritageModel);
    }

    private void startJFrame(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(BorderLayout.NORTH, jLabel);
        add(BorderLayout.CENTER, new JScrollPane((jTable)));
        add(BorderLayout.SOUTH,panel);

        setBounds(0, 0, 800, 600);
        setVisible(true);

        //테이블 행 선택시 문화재 번호 가져 옴.
        ListSelectionModel listSelectionModel=jTable.getSelectionModel();
        listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {   //하나가 선택이 되어 졌을때 콘솔에 인덱스 출력
                    ListSelectionModel listSelectionModel1 = (ListSelectionModel) e.getSource();
                    rowIndex = listSelectionModel.getMinSelectionIndex();
                    System.out.println("rowIdx: " + rowIndex);
                    //선택이 되어진 행의 디테일 객체 생성.
                    if (rowIndex != -1) {
                        String heritageArray = heritageItems[rowIndex][0];
                        new HeritageDetailView(heritageArray);
                        rowIndex = -1;
                    }
                }
            }
        });

        panel.add(btnPrevious);
        panel.add(btnNext);

        //리스너와 연결
        btnNext.addActionListener(this);
        btnPrevious.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnNext){
            pageNum++;
            beginNum=heritageNumPerPage*pageNum+1;
            endNum=heritageNumPerPage*(pageNum+1);
            arrayList=heritageController.receiveData(beginNum,endNum);
            loadTableData();
        }else if(e.getSource()==btnPrevious){
            if(pageNum>0)
                pageNum--;
            beginNum=heritageNumPerPage*pageNum+1;
            endNum=heritageNumPerPage*(pageNum+1);
            arrayList=heritageController.receiveData(beginNum,endNum);
            loadTableData();
        }
    }
}



















