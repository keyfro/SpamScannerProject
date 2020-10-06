package SpamScannerProject;

public class SpamScanner {
	
	private String[] keyWords = {"money", "cash","million", "won","win", 
    		"select", "buy", "opportunity", "fee", "online", "work", "income", 
    		"eliminate", "low", "interest", "approve", "avoid", "satisfaction", 
    		"guarantee", "risk", "free", "purchase", "save", "limite", 
    		"offer", "obligation", "click", "collect", "claim", "bankruptcy"};
    
    
    public static int countWords(String value) {
  	        String[] words = value.split("\\W+");

  	        if (words.length == 1 && words[0].length() == 0) {
  	            return 0;
  	        }
  	        return words.length;
  	        
    }
    
    public static final int numbOccurrences(String line, String spamWord) {
    	int occurences = 0;
    	int index = 0;
    	
    	while (index != -1) {
          index = line.toLowerCase().indexOf(spamWord, index);

          if (index != -1) {
              index += spamWord.length();
              occurences++;
          }
      }
      
      return occurences;
  }
  
  public int countSpam(String text) {
      int count = 0;
      
      for (String word : keyWords) {
          count += numbOccurrences(text, word);
      }
      
      return count;
  }
  
}
  



