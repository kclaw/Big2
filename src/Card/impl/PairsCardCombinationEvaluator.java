package Card.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import Card.Card;
import Card.CardCombination;
import Card.CardCombinationEvaluator;
import Card.CardCombinationValue;
import Card.CardRank;
import Card.CardSuit;

public class PairsCardCombinationEvaluator implements CardCombinationEvaluator<List<CardCombination>>{

	private Workbook wb;
	
	
	public  PairsCardCombinationEvaluator(InputStream stream) {
		try {
			wb = WorkbookFactory.create(stream);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	@Override
	public List<CardCombination> evaluate() {
		List<CardCombination> pairs = new ArrayList<>();
        Sheet sheet = wb.getSheetAt(0);  
        for(int i=53;i<131;i++) {
        	 Row row = sheet.getRow(i);  
             Cell cell1 = row.getCell(0); 
             Cell cell2 = row.getCell(1);
             if (null!=cell1 && null!=cell2) {
            	DataFormatter formatter = new DataFormatter();
             	pairs.add(new PairsCardCombination.PairsCardCombinationBuilder()
             			.addCard(new Card(CardRank.getCardRankByNumber(formatter.formatCellValue(cell1)), CardSuit.getCardSuitByAbbreviation(row.getCell(5).getStringCellValue())))
             			.addCard(new Card(CardRank.getCardRankByNumber(formatter.formatCellValue(cell2)), CardSuit.getCardSuitByAbbreviation(row.getCell(6).getStringCellValue())))
             			.setCardCombinationValue(new CardCombinationValue((int) row.getCell(10).getNumericCellValue()))
             			.build());
             	//System.out.println(CardRank.getCardRankByNumber(formatter.formatCellValue(rank)));
             }
        }
        return pairs;
	
	}

	@Override
	public void close() {
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
