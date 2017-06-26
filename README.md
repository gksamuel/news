# news

######## newsReader

An application that fetches news articles from reuters and saves to the database.
This is designed to be run as a scheduled task
The main class is listed below
https://github.com/gksamuel/news/blob/master/newsReader/src/main/java/reader/FeedReader.java


######## pageRank

An application that computes the pageRank and updates the news articles
This is designed to be run as a scheduled task

######## hivisasanews
A web application that displays the news to the consumer

######## Classes of interest

Web Page
https://github.com/gksamuel/news/blob/master/hivisasanews/src/main/webapp/index.xhtml
This is a single page that displays all pages for the entire site

Classes
https://github.com/gksamuel/news/blob/master/hivisasanews/src/main/java/com/gachanja/hivisasanews/News.java
This is the main class that retrieves the news from the database


https://github.com/gksamuel/news/blob/master/hivisasanews/src/main/java/com/gachanja/hivisasanews/NewsArticle.java
This is a news article model class


https://github.com/gksamuel/news/blob/master/hivisasanews/src/main/java/com/gachanja/hivisasanews/NewsCategory.java
This is a news article category model

