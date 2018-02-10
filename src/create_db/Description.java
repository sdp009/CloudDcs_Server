/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package create_db;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Description {

	public static final String GOOGLE_SEARCH_URL = "https://www.google.co.in/search";
	public String describe(String args) throws IOException {
		//Taking search term input from console
		//Scanner scanner = new Scanner(System.in);
		//System.out.println("Please enter the search term.");
		//String searchTerm = scanner.nextLine();
		String searchTerm = "what is " + args + " application?";
                String linkText="";
                //System.out.println("Please enter the number of results. Example: 5 10 20");
		int num = 2;
		//scanner.close();
		
		String searchURL = GOOGLE_SEARCH_URL + "?q="+searchTerm+"&num="+num;
		//without proper User-Agent, we will get 403 error
		Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();
		
		//below will print HTML data, save it to a file and open in browser to compare
		//System.out.println(doc.html());
		
		//If google search results HTML change the <h3 class="r" to <h3 class="r1"
		//we need to change below accordingly
		Elements results = doc.select("span.st");

		for (Element result : results) {
			//String linkHref = result.attr("contents");
			linkText += result.text() + "\n";
			//System.out.println("Text::" +linkText);
		}
            return linkText;
	}
        
        public static void mainTester(String s[]) throws IOException{
            Description d = new Description();
            String ans = d.describe("armitage");
            
            System.out.println("Desc=> " + ans);
        }
       

}