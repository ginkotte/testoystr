package testeOystr;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class testeHtml {

	public static void main(String[] args) throws IOException {

		int contaExterno = 0;
		int contaInterno = 0;

		Scanner inputUrl = new Scanner(System.in);
		System.out.println("Insira o url desejado: ");
		String url = inputUrl.nextLine();
		Document doc = Jsoup.connect(url).get();
		String html = doc.toString();

		String title = doc.title();
		System.out.println("O titulo é: " + title);

		Pattern pattern = Pattern.compile("<!doctype html>");
		Matcher matcher = pattern.matcher(html);
		boolean encontrou = matcher.find();

		if (encontrou) {
			System.out.println("Este site utiliza a versão 5 do HTML");
		} else {
			System.out.println("Este site utiliza uma versão diferente da 5 do HTML");
		}

		Elements links = doc.select("a[href]");
		for (Element link : links) {
			//System.out.println(link.attr("href"));
			String href = link.attr("href").toString();

			if (href.contains("http")) {
				contaExterno += 1;
			} else {
				contaInterno += 1;
			}
		}

		System.out.println("Links internos: " + contaInterno);
		System.out.println("Links externos: " + contaExterno);

	}

}
