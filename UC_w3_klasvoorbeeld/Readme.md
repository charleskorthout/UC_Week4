# practicumopdracht: Huffman codering

## Doelstellingen

In deze opdracht komen de volgende onderwerpen aan bod:
 
- Het aanmaken, gebruiken en aanpassen van datastructuren.
- Het sorteren van een datastructuur middels het Java Collections Framework.
- Het bepalen van de orde van een algoritme.
- Het maken en recursief doorlopen van een boom.


## Datacompressie

Een bekende toepassing van (operaties op) datastructuren is datacompressie.
Datacompressie is het op een dusdanige manier representeren van data, dat de hoeveelheid bits benodigd om de data te beschrijven af neemt. De vertaalslag van gecomprimeerde data naar (een benadering van) de oorspronkelijke data wordt decompressie genoemd.

Twee typen compressie kunnen worden onderscheiden:
- Lossless compressie: compressie zonder verlies van data, decompressie levert altijd exact de originele data op. Programma's die gebruik maken van lossless compressie zijn bijvoorbeeld Flac, WinZip en WinRar.
- Lossy compressie: compressie waarbij verlies van data acceptabel wordt geacht als dit tot betere compressie leidt, vaak levert decompressie een benadering van de originele data op. Denk bijvoorbeeld aan JPEG compressie voor foto's, of het comprimeren van muziek met MP3 codering.


## Opdracht

De opdracht omvat het programmeren van een specifieke lossless compressie variant, namelijk Huffmancodering.

Het Huffman algoritme wordt uitgelegd in de Huffman_1,2,3,.. PDFjes en UC_w3_huffman.ppt. Neem eerst deze documenten door om een overzicht te krijgen van de benodigde stappen.
Zoals je kunt lezen bestaat het algoritme uit een zestal stappen. Het is de bedoeling deze stappen te implementeren in Java. Het is daarom ook handig je Java applicatie naar deze stappen in te delen.

Gezien de beschikbare tijd hoeft geen tijd te worden besteed aan een grafische user interface. Hetzelfde geldt voor het inlezen van de te comprimeren data of het wegschrijven van de gecomprimeerde data en codes.

Ga er vanuit dat de te comprimeren data een met de hand gevulde datastructuur is. Bijvoorbeeld een ArrayList gevuld met Character objecten. Voor het tussentijds testen is het handig om een kleine hoeveelheid gemakkelijk te tonen data te gebruiken. Bijvoorbeeld de karakters van een (1) woord.

Tip: aangezien de stappen verder werken met data uit voorgaande stappen is het verstandig om de werking van iedere afzonderlijk stap inzichtelijk te maken. Maak hiervoor unit testen.


### Stap 1: Frequentie van de tekens tellen

Bedenk en specificeer een functie die telt hoe vaak welke karakters voorkomen in de te coderen data (=de frequentie van de karakters). 

De funtie moet:
- voor een gegeven tekst het eerstvolgd karakter nemen.
- het karakter toevoegen aan een groep met hetzelfde karakter, als er geen groep is een nieuwe groep aanmaken
- als alle karakters in de tekst verwerkt zijn het aantal elementen in de groepen tellen
- de tuples met karakter en een aantal sorteren op het aantal. Het tuple met het meeste elelement komt vooraan daarna het tuple met het op een na meeste elementen etc.

Een wat meer formelere functionele implementatie (bijvoorbeeld in F#) ziet er als volgt uit


``` F#

open System

let str = "qwertyuioqqwertyuioqwertyuioasdfgQWERETYUIKMMNBVCXDFGYUIOKL"

let countFreq (s:string) = 
    s.ToCharArray()
    |> Seq.countBy(fun x -> x)
    |> Seq.sortBy(fun (x,y) -> -y) 


[<EntryPoint>]
let main argv = 
    printfn "%A" (countFreq str) |> ignore
    Console.ReadLine() |> ignore
    0 // return an integer exit code


```   

Het reseultaat levert dan de volgende lijst van tuples op

Seq [('q', 4); ('w', 3); ('e', 3); ('r', 3); ...]

#### Vraag 1a. 
Maak unittesten die tenminste 3 relevante inputs testen. Implementeer pas daarna de code. 

##### Test 1
Een test met de lege string

``` java
@Test
void testWithEmptyInput() {
    String str = null;
    List<Char,int> list = countFreq(str);
    int expected = 0;
    int actual = list.size();
    Assert.Equals(expected,actual);
}

```

##### Test 2
Een test met een string met 1 karakter

``` java 

@Test
void testWithSingleCharacter() {

    String str = "A";
    List<Char,int> list = countFreq(str);
    int expected = 1;
    int actual = list.size();
    Assert.Equals(expected,actual);
}

```

##### Test 3
Een test met een string met een x aantal elements is geselecteerd in aflopende orde

``` java

@Test
void frequencyCountIsProperlySorted() {
    String str = "qwertyuioqqwertyuioqwertyuioasdfgQWERETYUIKMMNBVCXDFGYUIOKL";
    List<Char,int> list = countFreq(str);
    bool expected = true;
    bool actual = isSortedInDescendingOrder(list);
    Assert.Equals(expected, actual);
}

bool isSortedInDescendingOrder(List<Char,int> list) {
    Iterator iterator = list.iterator();
    if (!iterator.next()) return true;
    <Char,int> t1 = iterator.next(); //TODO fixed th etype
            while (iter.hasNext()) {
            <Char,int> t2 = iter.next();
            if ((snd t1).compareTo((snd t2)) > 0) {
                return false;
            }
            t1 = t2;
        }
        return true;
}

class FreqNode {
    Char char;
    int i;
    
    FreqNode(Char c, int i){
        char =c;
        this.i = i;
    }
    
    // plus
    
    // fst 
    
    // snd 
}
```

De resultaten van deze functie worden in de volgende stap gesorteerd, dus het is handig om de resultaten als objecten op te slaan in een verzameling die ofwel gesorteerd kan worden, ofwel al gesorteerd is.

#### Vraag 1b. 
Kies zelf een collection waarin je de objecten opslaat die nodig zijn voor het tellen van de frequentie. Beargumenteer je keuze.

Als mogelijke collectie om de resultaten op te slaan komen de volgende structuren in aanmerking:

##### HashMap
- HashMap Het voordeel van een HashMap is dat de toevoeging en telling redelijk eenvoudig is.
Een mogelijke implementatie wordt dan

``` java 

String str = "qwertyuioqqwertyuioqwertyuioasdfgQWERETYUIKMMNBVCXDFGYUIOKL"


static HashMap<Character, Integer> countFreq(String text) {
    char[] chars = text.toCharArray();
    HashMap<Character, Integer> countMap = new HashMap<Character, Integer>();
    for (char aChar : chars) {
        if (countMap.containsKey(aChar)) {
            countMap.put(aChar, countMap.get(aChar) + 1);
        } else {
            countMap.put(aChar,1);
        }
    }

    //sort the occurences ..
    // TODO 
    
    // return the HashMap
    return countMap;
}

```

##### Orde van het algoritme

De orde van de invoeging is O(1) bij een goede keuze van de haskkey, in het slechste geval is het algoritme tussen O(n(log(n))) en O(n^2) zijn.

##### Geheugengebruik en iteraties

Het geheugen gebruik van de HashMap is ongeveer 5 * 255 bytes voor de Hashmap 
- 1 byte voor het opslaan van het karakter (max 255 karakters) en 4 bytes voor het opslaan van het aantal.

Voor geheugen gebruik zijn er bij de quicksort wordt meestal log(n) aangehouden voor het opslaan van de stack frames


##### Array
- Ook voor een Array geldt dat de implementatie eenvoudig en ook het tellen simpel is.
Omdat we kijken naar karakters is de grootte van de array beperkt tot 255 elementen. Een funtie die een karakter omzet naar de Byte waarde bepaalde de positie in de Array.


Een mogelijke implementatie wordt dan

``` java 

String str = "qwertyuioqqwertyuioqwertyuioasdfgQWERETYUIKMMNBVCXDFGYUIOKL"


static Int[] countFreq(String text) {
    char[] chars = text.toCharArray();
    int[] countMap = new int[255]; // already initalizes to 0
    for (char aChar : chars) {
        countMap[(byte) aChar] = count[(byte) aChar] + 1)) 
    }

    //sort the occurences ..
    // TODO 
    
    // return the HashMap
    return countMap;
}

```

##### Orde van het algoritme

De orde van de invoeging is O(1). De sort is even opletten omdat we de waarde van het karakter kwijt raken. 
We zullen dit dus moeten toevoegen aan de data structuur voordat we kunnen sorteren. Dit kost een extra O(1). De sort is vergelijkbaar met de quicksort hierboven. O(n(log(n))) en O(n^2) zijn.

##### Geheugengebruik en iteraties

Het geheugen gebruik van de Array is ongeveer 4 * 255 bytes voor opslaan van de tellingen 

Voor geheugen gebruik zijn er bij de quicksort wordt meestal log(n) aangehouden voor het opslaan van de stack frames


### Stap 2: Tekens op frequentie sorteren

In de vorige stap is bepaald hoe vaak welke karakters voorkomen, of anders gezegd: wat de frequentie van de karakters is. Om gemakkelijk een Huffman-boom te kunnen maken moeten de karakter/frequentie combinaties op frequentie worden gesorteerd.

#### Vraag 2a. 

Kies een datastructuur om je karakters op frequentie te kunnen sorteren. Beargumenteer je keuze.
Erg belangrijk in de keuze van de datastructuur is of we een in-place sortering moeten doen of dat we kunnen invoegen in een externe lijst.
Omdat het hier gaat over relatief kleine lijsten is een mogelijke structuur is een gesorteerde lijst, omdat het aantal verschillend karakters klein is, bjvoorbeeld 255 bij een single byte karakter of 64k bij een twee byte karakter is het eenvoudig om de ene lijst een-voor-een te lezen in in tre voegen in de gesorteerde lijst.
<br>
Op kleine collecties is het meestal sneller om de lijst opnieuw op te bouwen, in de lijst kunnen dubbelen voorkomen, een gesorteerde lijst, hashMap of een Bag is waarschijnlijk de beste oplossing. Een voorbeeld van een implementatie kan dan zijn met een balanced tree (n log(n)) voor  
In het antwoord voor de vorige vraag hadden we een array gebruikt om de frequenties te tellen, waarbij we de byte waarde gebruikte als array index.

Laten we hiervoor nu een nieuwe (generieke) klasse introduceren, gebaseerd op wat we hierboven al met de HashMap hadden gemaakt en wat we nu FrequencyCounter noemen

Om een meer generieke oplossing te maken heb ik in Maart 2018 de structuur gewijzigd. Ik heb een nieuw package gemaakt; Compression met 2 nieuwe interfaces:
- Decoder 
- Encoder.

De structuuur ziet er als volgt uit:

```java

package Compression;

public interface Encoder<S,T> {
    /**
     * Encode from the source format into the target format
     * @param s The source format for example String
     * @return the encoded data in the target format
     */
    T encode(S s);
}
```
De bijbehoordende decoder ziet er als volgt uit

```java

package Compression;

public interface Decoder<T, S> {
    /**
     * Decodes encoded data to the source format 
     * @param t the encoded data 
     * @return the decoded data
     */
    S decode(T t);
}

```

Een veelvoorkomde oplossing in compressiealgoritme is het tellen van hoevaak een charater of structuur voorkomt.
Hiervoor maken we een structuur aan die we onderbrengen in een hulpmiddelen package.


``` java
package Compression.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


/**
 * Created by charles korthout on 4/2/2017.
 * March 11 2018 Changed the frequency counter to return sorted stream and moved to utils package
 */
public class FrequencyCounter<T extends Comparable<T>> {

    private final HashMap<T, Integer> frequencies = new HashMap<>();

    /**
    * Increments the count of the given type,
    * setting it to one on first appearance.
    * @param t the type to count
    */
    public void increment(T t) {
        Integer freq = frequencies.get(t);
        if (freq == null) {
            frequencies.put(t, 1);
        } else {
            frequencies.put(t, freq + 1);
        }
    }

    /**
     * Returns the sorted stream of characters and their (descending) frequencies
     * https://stackoverflow.com/questions/109383/sort-a-mapkey-value-by-values
     */
    Stream<Map.Entry<T,Integer>> getCounts =
            frequencies.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue());
}

```

Ik ben nog steeds niet overtuigd over dit fragment. Algemeen wordt aangegeven dat Hashmap met de invoering van streams 'legacy' code is en dat het beter is om <code>Map</code> te gebruiken.
Op basis hiervan de volgende refactoring gedaan:

``` java 

package Compression.Utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by charles korthout on 4/2/2017.
 * March 12 2018 Refactored the Hashmap to streams
 */
public class FrequencyCounter<T extends Comparable<T>> {

    /**
     * Calcualte the frequencies of elements in a stream
     * @param stream The stream of elements
     * @return A map with elements and their frequencies
     */
    public final Map<T,Long> getFrequencies(Stream<T> stream) {
        // Group the elements in a stream based on there identity and count the occurences
        Map<T, Long> result = stream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<T, Long> frequencies = new LinkedHashMap<>();
        //Sort the map based on frequencies in asscending order (lowest values first)
        result.entrySet().stream()
                .sorted(Map.Entry.<T, Long>comparingByValue())
                .forEachOrdered(e -> frequencies.put(e.getKey(), e.getValue()));
        return frequencies;
    }
}

```
De telling gebeurd nu door de groupBy collector in de stream.  Een tweede stap is om hierna de sortering te doen zodat de kleinste waardes vooraan staan.
Nu gebeurd dit nog door een extra stap uit te voeren. Een verbetering kan zijn door dit in een stap te zetten.

```java
    public final Map<T,Long> getFrequencies(Stream<T> stream) {
        Map<T, Long> frequencies = new LinkedHashMap<>();
        // Group the elements in a stream based on there identity and count the occurences
        stream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                                 .entrySet()
                                 .stream()
                                 .sorted(Map.Entry.<T, Long>comparingByValue())
                                 .forEachOrdered(e -> frequencies.put(e.getKey(), e.getValue()));;
        return frequencies;
    }
```


We kunnen nu eenvoudig de frequency counter for een stuk tekst maken, met de volgende test stubs.
De testen worden ondergebracht in een separaat package; CompressionTests

We hebben de volgende tests:
- bij gelijke karakters
- bij dezelfde karakters
- bij zowel gelijke als verschillende karakters
- check op gesorteerdheid.

De check op gesorteerdheid voor streams is een complexe. Die wordt hier uitgevoerd in twee fases; op basis van een iterator
 wordt de gesorteerdheid gecontroleerd. Deze routine wordt dan aangeroepen met de iterator van de stream.
 
```java
/**
     * Check if an collection is sorted in descending order
     * @param iterator the collection that can be iterated
     * @param <T> The element
     * @return true if sorted, false otherwise
     */
    private static <T extends Comparable<? super T>> boolean isSorted(Iterator<T> iterator) {
        if (!iterator.hasNext()) {
            return true;
        }
        T t = iterator.next();
        while (iterator.hasNext()) {
            T t2 = iterator.next();
            if (t.compareTo(t2) > 0) {
                return false;
            }
            t = t2;
        }
        return true;
    }

    /**
     * Check if a stream is sorted
     * @param stream the stream of elements
     * @param <T> The type of elements
     * @return true if the stream is sorted, false otherwise
     */
    private static <T extends Comparable<? super T>> boolean isSorted(Stream<T> stream) {
        return CharacterCounterTest.isSorted(stream.iterator());
    }

```
Dit is dan te gebruiken in de testen

``` java 
package CompressionTests;

import Compression.Utils.CharacterCounter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

class CharacterCounterTest {

    
    /**
     * Test that identical characters give the appropriate count
     */
    @Test
    void getCharacterCountsWhenAllCharactersAreSame() {
        String test = "aaaaaa";
        Map<Character, Long> counts = CharacterCounter.getFrequencies(test);
        System.out.println(counts);
        int expected = 1;
        int actual = counts.size();
        assertEquals(expected,actual, "Expexting a frequency of 6");
    }

    /**
     * Test that different characters give the appropriate count
     */
    @Test
    void getCharacterCountsWhenAllCharactersAreDifferent() {
        String test = "abcdef";
        Map<Character, Long> counts = CharacterCounter.getFrequencies(test);
        System.out.println(counts);
        long expected = 6;
        long actual = counts.size();
        assertEquals(expected,actual, "Expexting a frequency of 6");
    }

    /**
     * Test that different characters give the appropriate count
     */
    @Test
    void getCharacterCountsWhenBothEqualAndDifferentCharacters() {
        String test = "aaabbc";
        Map<Character, Long> counts = CharacterCounter.getFrequencies(test);
        System.out.println(counts);
        long expected = 3;
        long actual = counts.size();
        assertEquals(expected,actual, "Expexting a frequency of 3");
    }

    /**
     * Test that different characters give the appropriate count
     */
    @Test
    void checkIfStreamIsSorted() {
        String test = "aaabbc";
        Map<Character, Long> counts = CharacterCounter.getFrequencies(test);
        System.out.println(counts);
        boolean expected = true;
        boolean actual = CharacterCounterTest.isSorted(counts.values().stream());
        assertEquals(expected,actual, "Expecting a sorted stream");
    }
}

```
De body van de CharacterCounter is nu eenvoudig in te vullen met de FrequencyCounter klasse.

``` java
package Compression.Utils;

import java.util.Map;

/**
 * Created by charles korthout on 4/2/2017.
 * March 2018, Refactored to use the FrequencyCounter refactored getFrequencies
 */
public class CharacterCounter {

    /**
     * Gets the characters and the frequency from a text
     * @param text The text with characters
     * @return A map of characters and their frequencies in descending sorted order
     */
    public final static Map<Character,Long> getFrequencies(String text) {
         FrequencyCounter counter = new FrequencyCounter();
         return counter.getFrequencies(text.chars().mapToObj(i-> (char)(i)));
    }
}


```

![ ](./img/2018-03-13_9-50-01.jpg)

We hebben nu een contract voor het coderen en decoderen en een hlpmiddel om karakters in een tekst te tellen.

De volgende stap is dat we een Huffman compressie voor een tekst te maken.

```java

package Compression;

/**
 * The Huffman text compressor compresses and decompresses a text to/from a string of zeroes and ones
 */
public class HuffmanTextCompressor implements Encoder<String, String>, Decoder<String,String> {

    @Override
    public String decode(String s) {
        return null;
    }

    @Override
    public String encode(String s) {
        return null;
    }
}

```

Een bijbehorende test is dat de gedecodeerde code gelijk moet zijn aan de ongecodeerde code.

```java

package CompressionTests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Compression.HuffmanTextCompressor;

class HuffmanTextCompressorTest {

    /**
     * The decoded, code message must match the inital text
     */
    @Test
    public void CodeTextDecodedMustMatchUncodeMessage(){
        HuffmanTextCompressor compressor = new HuffmanTextCompressor();
        String actual = "This is text";
        String expected = compressor.decode(compressor.encode(actual));
        assertEquals(expected, actual);
    }
}
```

We hebben nu een structuur nodig om waardes op te slaan. Een snelle data structuur om de waardes gesorteerd op te slaan is een Binary Search Tree (BST). Hier worden waardes die kleiner zijn in de linker brach opgeslagen en waardes die groter in de rechter branch. In ons voorbeeld is het echter mogelijk dat er dubbelen zijn. We kunnen daarom kiezen om de waardes kleiner <b>of gelijk aan </b> in de linker brach op te nemen en waardes groter in de rechter branch. een tweede mogelijkheid is om een lijst van alle waardes die gelijk zijn aan te houden in de parent.
De laatste zal waarschijnlijk sneller zijn als er veel identieke waardes zijn. Laten we voor de laatste implementatie kiezen.

``` java 
package Huffman;

import java.util.*;
import java.util.Map.Entry;

/**
 * Created by charles korthout on 4/2/2017.
 */
public class HuffmanNode {
    List<Character> chars; // hold the list of all different characters that have the same frequency count
    Integer frequency; // the frequency count
    HuffmanNode leftbranch; // the nodes with frequency counts that are smaller than value
    HuffmanNode rightbranch; // the nodes with frequency counts that are larger than value.

    /**
     * De default constructor.
     * @param character The character to add
     * @param frequency The frequency count
     * @param left the bracnh with values that are smaller than the current frequency count
     * @param right The branch with values that are larger than the current frequency count.
     */
    HuffmanNode(char character, int frequency,  HuffmanNode left,  HuffmanNode right) {
        this.chars = new ArrayList<>();
        chars.add(character);
        this.frequency = frequency;
        this.leftbranch = left;
        this.rightbranch = right;
    }

    List<Character> getCharacters() {
        return chars;
    }

    int getFrequency() {
        return frequency;
    }
```

We moeten er nu ook voor zorgen dat we de HuffmanNode met elkaar kunnen vergelijken, dus we zorgen dat we ook de class Comparable implementeren

``` java 

public class HuffmanNode implements Comparable<HuffmanNode>{

...

     @Override
     public int compareTo(HuffmanNode node) {
             return frequency - node.frequency; // The old integer compare trick...
     }
     
     @Override
     public boolean equals(Object o) {
         if (null==o) return false;
         if (!(o instanceof HuffmanNode)) return false;
         HuffmanNode node = (HuffmanNode) o;
         return frequency == node.frequency;
     }
     
     @Override
     public int hashCode(){
         return frequency.hashCode();
     }

```
#### Refactoring maart 2018
Omdat de nieuwe tel routine het type <code>Long</code> gebruikt moeten we de compare ook aanpassen. De nieuwe <code>compareTo</code> methode wordt nu:

```java

/**
         * The compare of the nodes is based on the frequencies
         * @param that The node to compare with
         * @return the comapre result
         */
        public int compareTo(HuffmanNode that) {
            return Long.compare(this.freq , that.freq);
        }

``` 
#### Einde refactoring maart 2018

### Huffman testen
Voordat we de daadwerkelijke implementatie van Huffman beginnen is het belangrijk om eerst wat testen te maken van het gedrag dat we verwachten van de Huffman node.
<br>
Uiteindelijk doel is om text te comprimeren (encrypt) en te decomprimeren met decrypt. De decrypt van de encrypt moet het originele bericht opleveren.
Het test bestand kan er dan als volgt uitzien.

``` java
package HuffmanTests;

import Huffman.HuffmanNode;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by charles korthout on 4/2/2017.
 */
class HuffmanNodeTest {
    HuffmanNode huffman = null;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    /**
     * The decrypt from the encrypted message must provide the original message
     */
    @org.junit.jupiter.api.Test
    void testEncryptAndDecrypt() {
        String test = "test";
        assertEquals(test, HuffmanNode.decrypt(HuffmanNode.encrypt(test)));
    }
}
```

#### Refactoring Maart 2018
Voor de nieuwe uitwerking van de Huffman codering hebben we nu slechts 2 publieke methodes; 
- <code>encode</code> Om een type te comprimeren
- <code>decode</code> Om een gecodeerd bericht te decoderen

De laatste test wordt nu:
```java
/**
     * The decoded, code message must match the inital text
     */
    @Test
    public void CodeTextDecodedMustMatchUncodeMessage(){
        HuffmanTextCompressor compressor = new HuffmanTextCompressor();
        String actual = "This is text";
        String expected = compressor.decode(compressor.encode(actual));
        assertEquals(expected, actual);
    }
```
#### Einde refactoring Maart 2018

We voegen de beide methodes toe aan de HuffmanNode

``` java
     /**
     * encrypt a text to a compressed form, using the Huffman coding
     * @param text The Text to compress
     * @return The encrypted string compressed by the Huffman compression algorithm
     */
    public static String encrypt(String text) {
        // TODO
        throw new NotImplementedException();
    }

    /**
     * Decrypts a Huffman encoded text message into the original text
     * @param codedText The message to decompress
     * @return The uncompressed text
     */
    public static String decrypt(String codedText) {
        // TODO
        throw new NotImplementedException();
    }

```
Dit geeft het volgende (verwachte) testresultaat

![ ](./img/2017-04-02_19-06-33.jpg) 

We moeten nu de Huffman boom maken op basis van de tekst die we aangeleverd krijgen.

``` java

/**
     * Build a Huffman tree from a text
     * @param text The text to code
     * @return an ordered Huffman tree for the text
     */
    public HuffmanNode buildTree(String text){
        // TODO check if text is not empty...
        Set<Map.Entry<Character, Integer>> counts = CharacterCounter.getCharacterCounts(text);
        HuffmanNode tree = null;
        Iterator iterator = counts.iterator();
        if (iterator.hasNext()) {
            Entry<Character,Integer> entry = (Entry) iterator.next();
            tree = new HuffmanNode(entry.getKey(),entry.getValue(), null,null);
        }
        while (iterator.hasNext()) {
            Entry<Character,Integer> entry = (Entry) iterator.next();
            HuffmanNode node = new HuffmanNode(entry.getKey(),entry.getValue(), null,null);
            tree.add(node);
        }
        return tree;
    }

```

#### Refactoring Maart 2018

De oplossing van afgelopen jaar was uitgebreider dan het m.i. noodzakelijk was. Er zijn enkele zaken die storend zijn:
- de twee stappen om de Huffman tree op te bouwen. 
- het geknutsel met de waarde bij een gecombineerd veld; hier wordt nu het karakter '\0' gebruikt.

Verder kan de Huffman codering generieker gemaakt worden. In de <code>FrequencyCounter</code> maken we gebruik van een generiek type <code>T</code>, die we in de <code>CharacterCounter</code> onmiddelijk casten naar <code>Character</code>. Wat als we die cast nu uitstellen?
  
Java versie 8 (en 9) hebben mogelijkheden om e.e.a. te verbeteren. 
Laten we beginnen met de Huffman tree zelf. Voor deze implementatie gebruiken we nu een <code>PriorityQueue</code>. Een van de kenmerken van deze datastructuur is dat de kleinste waarde vooraan staat.

Verder willen we nogmaals herhalen wat de kern van het Huffman algoritme is:
- vul de <code>PriorityQueue</code> met alle elementen uit de element tellingen.
- pak de kleinste elementen, voeg die samen tot een nieuw element, waarbij de frequentie de toetaal waarde krijgt van de frequentie uit de twee nodes.
- herhaal de procedure totdat er slechts een node in de queue overblijft.
- loop tot slot door de alle nodes om de codes te bepalen.

De methode <code>buildTree</code> ziet er dan als volgt uit:

```java
/**
         * Build a Huffman tree from a text
         * @param text The text to code
         * @return an ordered Huffman tree for the text
         */
        private static HuffmanNode buildTree(String text) {
            Queue<HuffmanNode> pq = new PriorityQueue<>();
            Iterator<Map.Entry<Character,Long>> frequencies = CharacterCounter.getFrequencies(text).entrySet().stream().iterator();
            while (frequencies.hasNext()){
                Map.Entry<Character,Long> entry = frequencies.next();
                pq.add(HuffmanNode.from(entry.getKey(),entry.getValue()));
            }
            while (pq.size() > 1) {
                //Remove two smallest elements.
                HuffmanNode node1 = pq.poll();
                HuffmanNode node2 = pq.poll();
                // Combine into a single node with these two as its children.
                pq.add(new HuffmanNode('\0', node1.freq+node2.freq, node1,node2));
            }
            // pq.size must be 1 so return the node
            return pq.poll();
        }
```

Zoals hierboven aangegeven zie je in de parameters dat we een tekst als parameter meegeven en in de body <code>Character</code> als element meenemmen. 

Verder valt op dat in de <code>add</code> een karakter '\0' als parameter wordt meegegeven. Laten we dat eerst m.b.v. de klasse <code>Optional</code> wat netter proberen op te lossen.

Een eerste stap is om de class <code>HuffmanNode</code> in een apart bestand op te nemen en meer generiek te maken en de class <code>Optional</code> te gebruiken voor het element.

```java
package Compression;

import java.util.Optional;

/**
 * A tree structure to hold the huffman elements and frequencies
 */
public class HuffmanNode<T> implements Comparable<HuffmanNode<T>> {
    private final Optional<T> element;
    private final Long freq;
    private HuffmanNode left, right;

    private HuffmanNode(Optional<T> element, Long freq, HuffmanNode<T> left, HuffmanNode<T> right) {
        this.element = element;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public HuffmanNode left() { return left; } 
    public HuffmanNode right() { return right; }
    public Long frequency() { return freq;}
    public Optional<T> element() { return element;}

    /**
     * Create a Huffman node from the element and the frequency
     * @param element The element
     * @param freq The total occurences of this element
     * @param <T> The type of the element
     * @return A Huffman node with the element and frequencies and empty subtrees
     */
    public static <T> HuffmanNode<T> from(T element, Long freq) {
        return new HuffmanNode(Optional.of(element), freq, null, null);
    }

    /**
     * Combines two Huffman nodes into a single node, and adds the frequencies of the subnodes
     * @param left The left node
     * @param right The right node
     * @param <T> The type of the element
     * @return A Huffman Node that is build from the two subnodes
     */
    public static <T> HuffmanNode<T> combine(HuffmanNode<T> left, HuffmanNode<T> right) {
        return new HuffmanNode(Optional.empty(), left.frequency() + right.frequency(), left, right);
    }
    /**
     * The Huffman node is a leaf node if both substrees are null
     *
     * @return the logical result of both subtrees being null
     */
    private boolean isLeaf() {
        return (left == null) && (right == null);
    }

    /**
     * The compare of the nodes is based on the frequencies
     *
     * @param that The node to compare with
     * @return the comapre result
     */
    public int compareTo(HuffmanNode<T> that) {
        return Long.compare(this.freq, that.freq);
    }

}

```
 
De <code>Huffman</code> nodes worden nu door de statische methodes <code>from</code> en <code>combine</code> die niet meer parameters bevatten dan strikt noodzakelijk.

We kunnen nu gaan kijken om de <code>HuffmanTextCompressor</code> te verbeteren.
Allereerst een naams verandering, de code moet eventueel generiek inzetbaar zijn. Dus <code>HuffmanCompressor</code> klinkt beter.

Verder hebben de interfaces <code>Encode</code> en <code>Decode</code> niet super veel toe te voegen. Beter zou zijn om dit statische factory methodes te maken in de <code>HuffmanCompressor</code>.

Allereerst de aanpassingen aan de <code>buildTree</code> methode. 

```java

      /**
     * Build a Huffman tree from a stream
     * @param elements the elements to process
     * @return an ordered Huffman tree for the elements
     */
    private static <T> HuffmanNode<T> buildTree(Stream<T> elements) {
        Queue<HuffmanNode> pq = new PriorityQueue<>();
        Iterator<Map.Entry<T,Long>> frequencies = FrequencyCounter.getFrequencies(elements).entrySet().iterator();
        while (frequencies.hasNext()){
            Map.Entry<T,Long> entry = frequencies.next();
            pq.add(HuffmanNode.from(entry.getKey(),entry.getValue()));
        }
        while (pq.size() > 1) {
            //Remove two smallest elements.
            HuffmanNode node1 = pq.poll();
            HuffmanNode node2 = pq.poll();
            // Combine into a single node with these two as its children.
            pq.add(HuffmanNode.combine(node1,node2));
        }
        // pq.size must be 1 so return the node
        return pq.poll();
    }

```
 
Wat opvalt in de bovenstaande code is dat alle referenties naar tekst zijn vervangen, ook is nu de <code>FrequencyCounter</code> direct aangeroepen.

Dit levert nog een probleempje op met de compilatie, de methode getFrequencies is nog niet als statische methode gedefineerd.

<code>



</code>

#### Vraag 2b. Van welke orde is het sorteer algoritme? 
We hebben nu een sorteer algoritme gemaakt. Het eerste gedeelte het lezen van de text is O(1). Het invoegen van in de boom is order log(n). (We beschouwen het toevoegen aan de lijst bij gelijkheid ook O(1)).Het opzetten is dus O(log(n))



#### Vraag 2c. Hoe geef je aan waarop er gesorteerd moet worden?
Er word gesorteerd op basis van het aantal maal dat een karakter voor komt in de tekst. Dit wordt eerst opgeslagen in een HashMap en daarna worden de elementen toegevoegd aan een BST met een lijst implementatie voor gelijkheid.

Een ander mogelijkheid is om gebruik te maken van een gesorteerde collection, bijvoorbeeld een PriorityQueue van het Java Collection Framework om de elementen gesorteerd op te vragen. Bestudeer de mogelijkheden van de PriorityQueue om een goede keuze kunnen maken.

Zorg er voor dat de lijst met karakter/frequentie combinaties op frequentie kan worden gesorteerd met behulp van het Java Collections framework.


### Stap 3: Maken van de Huffman-boom

Nu we de lijst kunnen sorteren kan de Huffman-boom worden opgebouwd. Specificeer hiertoe een methode die dit kan, en maak hiervoor unittests (goede testcases kunnen zijn hoe je vanuit fig 6.26 naar fig 6.27 komt (en verder). Creeer pas daarna de code.

Tijdens het maken van een boom vervang je telkens twee knopen door een nieuwe knoop met de originele knopen als kinderen. Door op het moment van samenvoegen uitvoer te genereren kun je achterhalen hoe de boom eruit ziet.

#### Vraag 3a. 
Maak een tekening van de boom. Vermeld bij iedere knoop de frequentie, en bij ieder blad de bijbehorende code. 

#### Vraag 3b: 
Wat is de benaming voor het type boom dat je getekend hebt? Waarom?
- ..
- ..


### Stap 4: Aflezen van de codes

Door middel van het doorlopen van de boom met een recursieve functie kunnen de bitreeksen behorend bij de codes worden uitgelezen.

### Vraag 4. 
Waarom is een recursieve functie bij uitstek geschikt om een boom te doorlopen?
- ..
- ..

Specificeer en implementeer de functie waarmee de codes kunnen worden afgelezen. Bekijk hiertoe de voorbeeldcode uit de les.


### Stap 5: Coderen van het bericht

Aan de hand van de codes kan de data worden gecomprimeerd. Kies hervoor een datastructuur die dit ondersteunt en een methode die dit daadwerkelijk uitvoert.
 
### Vraag 5a. 
Wat is de orde van het algoritme dat je gebruikt hebt? 
- ..
- ..

In principe zou je stap 4 over hebben kunnen slaan en voor iedere te comprimeren karakter de code in de boom hebben kunnen opzoeken. 

### Vraag 5b. 
Waarom zou stap 4 in het algoritme zijn opgenomen? 
- ..
- ..
- .. 

Vergelijk de grootte in bits van de gecomprimeerde en gedecomprimeerde data. Tel hierbij elk character dat een bit voorstelt als ��n bit (dus niet als character van 8 of 16 bits). 
### Optioneel:
Indien je elke codering echt bit voor bit wilt opslaan i.p.v. als een string, gebruik dan een BitSet. 


### Stap 6: Decoderen

Aan de hand van de boom of lijst van codes kun je het gecomprimeerde bericht weer decomprimeren tot de originele data.

Kies hervoor een datastructuur die dit ondersteunt en een methode die dit daadwerkelijk uitvoert. Deze methode moet van orde O(n) zijn, waarbij n het aantal gecomprimeerde tekens in het bericht is.










