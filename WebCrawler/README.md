
# What is a WebCrawler?
- A Web Crawler is a software that visits the Web and finds new or updated pages for indexing.
- Generally, the Crawler starts with seed websites or a wide range of popular URLs (aka frontier) and
  searches in depth and width for hyperlinks to extract.

# Properties of a Good WebCrawler?
- A Web Crawler must be kind and robust.
- Kindness for a Crawler means that it respects the rules set by the robots.txt and avoids visiting a website too often.
- Robustness refers to the ability to avoid spider traps and other malicious behavior.
- Other good attributes for a Web Crawler is distributivity amongst multiple distributed machines,
  expandability, continuity and ability to prioritize based on page quality.

# Simple Algorithm to create WebCrawler
The basic steps to write a WebCrawler are:

1. Pick a URL from the frontier
2. Fetch the HTML code
3. Parse the HTML to extract links to other URLs
4. Check if you have already crawled the URLs and/or if you have seen the same content before
   If not add it to the index
5. For each extracted URL
   Confirm that it agrees to be checked (robots.txt, crawling frequency)

# Implementation in Java
- For HTML parsing we can make use of jsoup.
```
<dependency>
    <groupId>org.jsoup</groupId>
    <artifactId>jsoup</artifactId>
    <version>1.10.2</version>
</dependency>
```

# Simple WebCrawler Architecture
![WebCrawler Architecture](https://frontera.readthedocs.io/en/v0.2.0/_images/frontier_01.png)

# What is a Crawler Frontier
- A crawl frontier is the part of a crawling system that decides the logic and policies to follow when
  a crawler is visiting websites (what pages should be crawled next, priorities and ordering, how often pages are revisited,   etc).
- The frontier is initialized with a list of start URLs, that we call the seeds. Once the frontier is initialized the crawler   asks it what pages should be visited next. As the crawler starts to visit the pages and obtains results, it will inform the   frontier of each page response and also of the extracted hyperlinks contained within the page. These links are added by the   frontier as new requests to visit according to the frontier policies.

# Detailed Architecture
![WebCrawler Architecture](https://frontera.readthedocs.io/en/v0.2.0/_images/frontier_02.png)
