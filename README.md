







# Crawling Java Webapp
This is a graphical web interface for the [crawling-java](https://github.com/juanfuria/crawling-java) library.
Please refer to the library for further information.

# Use
The crawler accepts a URL, e.g.: http://example.com and follows all links inside the same domain.
It outputs a list divided between internal and external links, it also attempts to classify internal links in different sections.

# Building
### Preconditions

- java 8 jdk
- maven 3.3.3 http://linuxg.net/how-to-install-apache-maven-3-2-1-on-ubuntu-14-04-linux-mint-17-and-their-derivative-systems/ the standard sudo apt-get install maven does not provide a version new enough.

```
$:~ git clone https://github.com/juanfuria/crawling-java-webapp.git
$:~ cd crawling-web-app
$:~ mvn clean package
```


# Deploying
### Deploying @ Heroku

```
$:~ git clone https://github.com/juanfuria/crawling-java-webapp.git
$:~ cd crawling-web-app
$:~ maven package
$:~ heroku deploy:war --war arget/crawling-java-webapp-1.0.war --app <app_name>
```
Alternatively you can deploy it to a web server that supports java web apps (Tomcat, Glassfish, etc...).
You can see a live demo [deployed at Heroku](https://crawling-webapp.herokuapp.com/)

# Trade-offs

The interface is simple and copies the one of the PHP app [See tag 1.0](https://github.com/juanfuria/crawling), but as there were no detailed specifications, this one has to work.
I really wanted to try out an HTML building library Rendersnake, and decided to use it for fun instead of a template (which should have been the option).
I also decided that I won't be using it in the future for any building that takes more than three lines.
