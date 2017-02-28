
#What is a WebCrawler?
- A Web Crawler is a software that visits the Web and finds new or updated pages for indexing.
- Generally, the Crawler starts with seed websites or a wide range of popular URLs (aka frontier) and
  searches in depth and width for hyperlinks to extract.

#Properties of a Good WebCrawler?
- A Web Crawler must be kind and robust.
- Kindness for a Crawler means that it respects the rules set by the robots.txt and avoids visiting a website too often.
- Robustness refers to the ability to avoid spider traps and other malicious behavior.
- Other good attributes for a Web Crawler is distributivity amongst multiple distributed machines,
  expandability, continuity and ability to prioritize based on page quality.

#Simple Algorithm to create WebCrawler
The basic steps to write a WebCrawler are:

1. Pick a URL from the frontier
2. Fetch the HTML code
3. Parse the HTML to extract links to other URLs
4. Check if you have already crawled the URLs and/or if you have seen the same content before
   If not add it to the index
5. For each extracted URL
   Confirm that it agrees to be checked (robots.txt, crawling frequency)

#Implementation in Java
- For HTML parsing we can make use of jsoup.
```
<dependency>
    <groupId>org.jsoup</groupId>
    <artifactId>jsoup</artifactId>
    <version>1.10.2</version>
</dependency>
```
