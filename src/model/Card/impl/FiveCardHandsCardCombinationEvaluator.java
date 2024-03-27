package model.Card.impl;

import java.io.FileInputStream;
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

import model.Card.Card;
import model.Card.CardCombination;
import model.Card.CardCombinationEvaluator;
import model.Card.CardCombinationFiveCardHandsType;
import model.Card.CardCombinationValue;
import model.Card.CardRank;
import model.Card.CardSuit;

public class FiveCardHandsCardCombinationEvaluator implements CardCombinationEvaluator<List<CardCombination>> {

	private Workbook wb = null;
	public FiveCardHandsCardCombinationEvaluator(InputStream stream) {
		try {
			wb = WorkbookFactory.create(stream);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	public FiveCardHandsCardCombinationEvaluator(String url) {
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
		List<CardCombination> hands = new ArrayList<>();
        Sheet sheet = wb.getSheetAt(0);  
        for(int i=145;i<299;i++) {
        	 Row row = sheet.getRow(i);  
             Cell cell1 = row.getCell(0); 
             Cell cell2 = row.getCell(1);
             Cell cell3 = row.getCell(2);
             Cell cell4 = row.getCell(3);
             Cell cell5 = row.getCell(4);
             if (null!=cell1 && null!=cell2 && null!=cell3 && null!=cell4 && null!=cell5) {
            	DataFormatter formatter = new DataFormatter();
            	hands.add(new FiveCardHandsCardCombination.FiveCardHandsCombinationBuilder()
             			.addCard(new Card(CardRank.getCardRankByNumber(formatter.formatCellValue(cell1)), CardSuit.getCardSuitByAbbreviation(row.getCell(5).getStringCellValue())))
             			.addCard(new Card(CardRank.getCardRankByNumber(formatter.formatCellValue(cell2)), CardSuit.getCardSuitByAbbreviation(row.getCell(6).getStringCellValue())))
             			.addCard(new Card(CardRank.getCardRankByNumber(formatter.formatCellValue(cell3)), CardSuit.getCardSuitByAbbreviation(row.getCell(7).getStringCellValue())))
             			.addCard(new Card(CardRank.getCardRankByNumber(formatter.formatCellValue(cell4)), CardSuit.getCardSuitByAbbreviation(row.getCell(8).getStringCellValue())))
             			.addCard(new Card(CardRank.getCardRankByNumber(formatter.formatCellValue(cell5)), CardSuit.getCardSuitByAbbreviation(row.getCell(9).getStringCellValue())))
             			.setCardCombinationValue(new CardCombinationValue((int) row.getCell(10).getNumericCellValue()))
             			.setHandType(convertToFiveCardHandsType(row.getCell(11).getStringCellValue()))
             			.build());
            }
        }
		return hands;
	}
	@Override
	public void close() {
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private CardCombinationFiveCardHandsType convertToFiveCardHandsType(String type) {
		switch (type) {
		   case "Straight":
			   return CardCombinationFiveCardHandsType.Straight;
		   case "Flush":
			   return CardCombinationFiveCardHandsType.Flush;
		   case "FullHouse":
			   return CardCombinationFiveCardHandsType.FullHouse;
		   case "FourOfAKind":
			   return CardCombinationFiveCardHandsType.FourOfACardPlusOneCard;
		   case "StraightFlush":
		   	   return CardCombinationFiveCardHandsType.StraightFlush;
		}
		return CardCombinationFiveCardHandsType.None;
	}

}
