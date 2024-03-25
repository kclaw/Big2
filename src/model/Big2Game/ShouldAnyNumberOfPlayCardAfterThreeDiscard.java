package model.Big2Game;

import java.util.*;

/**
 * 
 */
public class ShouldAnyNumberOfPlayCardAfterThreeDiscard implements PlaySpecification{

    
    public ShouldAnyNumberOfPlayCardAfterThreeDiscard() {
    }

	@Override
	public boolean isValidPlay(List<PlayRecord> records) {
		if (records.size()==3){
			if (records.get(0).discard == true)
				if (records.get(1).discard == true)
					if (records.get(2).discard == true)
						return true;
		}
		return false;
	}

}