package model.Card.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import model.Card.Card;
import model.Card.CardCombination;
import model.Card.CardCombinationEvaluator;
import model.Card.CardCombinationValue;
import model.Card.CardRank;
import model.Card.CardSuit;

public class SingleCardCombinationEvaluator implements CardCombinationEvaluator<List<CardCombination>> {

	private Workbook wb = null;
	public SingleCardCombinationEvaluator(InputStream stream) {
		try {
			wb = WorkbookFactory.create(stream);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	public SingleCardCombinationEvaluator(String url) {
		try {
			InputStream stream = new FileInputStream(url);
			wb = WorkbookFactory.create(stream);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	@Override
	public List<CardCombination> evaluate() {
		List<CardCombination> singles = new ArrayList<>();
            Sheet sheet = wb.getSheetAt(0);  
            for(int i=1;i<53;i++) {
            	 Row row = sheet.getRow(i);  
                 Cell rank = row.getCell(0); 
                 if (null!=rank) {
                	DataFormatter formatter = new DataFormatter();
                 	singles.add((SingleCardCombination) new SingleCardCombination.SingleCardCombinationBuilder()
                 			.addCard(new Card(CardRank.getCardRankByNumber(formatter.formatCellValue(rank)), CardSuit.getCardSuitByAbbreviation(row.getCell(5).getStringCellValue())))
                 			.setCardCombinationValue(new CardCombinationValue((int) row.getCell(10).getNumericCellValue()))
                 			.build());
                 }
            }
		return singles;
		
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
