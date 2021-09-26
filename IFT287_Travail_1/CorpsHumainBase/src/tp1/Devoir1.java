package tp1;


import org.w3c.dom.*;
import org.xml.sax.helpers.*;

import javax.json.*;
import javax.json.stream.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.*;

public class Devoir1 {

	private static final String CMD_IMPORTER = "importer";
	private static final String CMD_EXPORTER = "exporter";
	private static final String CMD_QUITTER = "quitter";
	private static final String TYPE_XML = "xml";
	private static final String TYPE_JSON = "json";

	private static Corps corps;
	private static Corps jsonCorps;

	public static void main(String[] args) {
		try {
			// Il est possible que vous ayez à déplacer la connexion ailleurs.
			// N'hésitez pas à le faire!
			BufferedReader reader = ouvrirFichier(args);
			String transaction = lireTransaction(reader);
			while (!finTransaction(transaction)) {
				executerTransaction(transaction);
				transaction = lireTransaction(reader);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static String getExtensionFichier(String nomFichier) {
		if (nomFichier.lastIndexOf(".") != -1 && nomFichier.lastIndexOf(".") != 0)
			return nomFichier.substring(nomFichier.lastIndexOf(".") + 1);
		else return "";
	}

	/**
	 * Decodage et traitement d'une transaction
	 */
	static void executerTransaction(String transaction) throws Exception, IFT287Exception {
		try {
			System.out.print(transaction + " ");
			// Decoupage de la transaction en mots
			StringTokenizer tokenizer = new StringTokenizer(transaction, " ");
			if (tokenizer.hasMoreTokens()) {
				String mode = tokenizer.nextToken();
				String nomFichier = readString(tokenizer);
				String extension = getExtensionFichier(nomFichier);

				if (mode.equals(CMD_IMPORTER)) {
					if (extension.equals(TYPE_XML)) {
						System.out.println("Debut de l'importation du fichier XML " + nomFichier);

						//***************
						// CODE PARTIE 2
						//****************
						//La partie importation de xml a orienté objet est basé sur les diapos 8 du ppw sur la Représentation des données - Framework
						SAXParserFactory factory = SAXParserFactory.newInstance();
						factory.setValidating(true);
						SAXParser parser = factory.newSAXParser();
						DefaultHandler handler = new SAXImportXml();
						parser.parse(new File(nomFichier), handler);
						corps = ((SAXImportXml)handler).getBody();


					} else if (extension.equals(TYPE_JSON)) {
						System.out.println("Debut de l'importation du fichier JSON " + nomFichier);
						//Votre code d'importation JSON ici (Partie 4)

						//La partie importation d'un fichier JSON est basé sur la diapositive 39 du ppw sur la Représentation des données - Framework
						JsonReader reader = Json.createReader(new FileReader(nomFichier));
						JsonStructure jsonStruct = reader.read();
						JsonObject corps = (JsonObject) jsonStruct;
						jsonCorps = new Corps(corps);

					} else {
						System.out.println("Le système ne supporte actuellement pas l'importation des fichiers au format " + extension);
					}
				} else if (mode.equals(CMD_EXPORTER)) {
					if (extension.equals(TYPE_XML)) {
						System.out.println("Debut de l'exportation vers le fichier XML " + nomFichier);
						// Votre code d'exportation XML ici (Partie 4)

						//La partie exportation vers le fichier xml est basé sur la diapositive 28 du ppw sur la Représentation des données - Framework

						DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
						try {
							DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
							Document document = documentBuilder.newDocument();
							corps.convertXML(document);

							// La source de ce code provient du site https://examples.javacodegeeks.com/core-java/xml/parsers/documentbuilderfactory/create-xml-file-in-java-using-dom-parser-example/
							TransformerFactory transformerFactory = TransformerFactory.newInstance();
							Transformer transformer = transformerFactory.newTransformer();
							transformer.setOutputProperty(OutputKeys.INDENT, "yes");
							DOMSource domSource = new DOMSource(document);
							StreamResult streamResult = new StreamResult(new File(nomFichier));

							transformer.transform(domSource, streamResult);

						} catch (ParserConfigurationException e) {

						}


					} else if (extension.equals(TYPE_JSON)) {
						System.out.println("Debut de l'exportation vers le fichier JSON " + nomFichier);
						//Votre code d'exportation JSON ici (Partie 3)

						Map<String, Object> map = new HashMap<String, Object>(1);
						// pour les tabs et espaces
						map.put(JsonGenerator.PRETTY_PRINTING, true);
						StringWriter w = new StringWriter();
						JsonGeneratorFactory f = Json.createGeneratorFactory(map);
						JsonGenerator jsonGenerator = f.createGenerator(w);

						FileWriter writer = new FileWriter(nomFichier);
						JsonGenerator generator = Json.createGenerator(writer);
						corps.convertJson(generator);
						generator.close();


					} else {
						System.out.println("Le système ne supporte actuellement pas l'exportation vers les fichiers au format " + extension);
					}
				} else {
					System.out.println("Commande inconnue, choisir entre 'importer' ou 'exporter'");
				}
			}
		} catch (
				Exception e) {
			System.out.println(" " + e.toString());
			e.printStackTrace();
		}

	}


	// ****************************************************************
	// *   Les methodes suivantes n'ont pas besoin d'etre modifiees   *
	// ****************************************************************

	/**
	 * Ouvre le fichier de transaction, ou lit à partir de System.in
	 */
	public static BufferedReader ouvrirFichier(String[] args) throws FileNotFoundException {
		if (args.length < 1)
			// Lecture au clavier
			return new BufferedReader(new InputStreamReader(System.in));
		else
			// Lecture dans le fichier passe en parametre
			return new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
	}

	/**
	 * Lecture d'une transaction
	 */
	static String lireTransaction(BufferedReader reader) throws IOException {
		return reader.readLine();
	}

	/**
	 * Verifie si la fin du traitement des transactions est atteinte.
	 */
	static boolean finTransaction(String transaction) {
		// fin de fichier atteinte
		return (transaction == null || transaction.equals(CMD_QUITTER));
	}

	/**
	 * Lecture d'une chaine de caracteres de la transaction entree a l'ecran
	 */
	static String readString(StringTokenizer tokenizer) throws Exception {
		if (tokenizer.hasMoreElements())
			return tokenizer.nextToken();
		else
			throw new Exception("Autre parametre attendu");
	}

}
