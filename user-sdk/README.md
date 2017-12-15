#Users SDK

## *The official SDK for the **microservice-users-and-teams** project by Lozzikit.*

## Introduction

Having a microservice able to register and authenticate users is good, being able to authenticate 
said users in your own application is better. The lozzikit team thought of everything and 
offers you the Users SDK project, the tools to authenticate users created using the 
**microservice-users-and-teams** microservice. 

## Maven dependency

You'll need to copy the following dependencies in your pom.xml:

```
<dependency>
    <groupId>io.lozzikit</groupId>
    <artifactId>users-sdk</artifactId>
    <version>0.0.1</version>
</dependency>
```

Since this SDK is not yet on the Maven central repository, you'll also need to add the following 
repository in your pom.xml:

```
<repositories>
    <repository>
        <id>myCustomRepository</id>
        <url>https://mymavenrepo.com/repo/WqnBJ2iHO7KunsQpuIjD/</url>
    </repository>
</repositories>
``` 

There, You're ready to use the SDK !

## Use the SDK in your project

### Tell Spring to scan the SDK components 

First, add the following line (if it doesn't already exist) in your application. If you already have this annotation, simply add the `io.lozzikit.sdk` package name to the list of base packages.

```
@ComponentScan(basePackages = { "io.lozzikit.sdk" })
```

### Protect your endpoints

What you'll want next is to use the SDK to protect some of your endpoints from non-authenticated users. Simply add the 
following annotation to said endpoints (or method for that matter):

```
@Authentication
```

**Example :**

```
@Controller
public class HomeController {
    @Authentication // The root of my API now needs authentication, awesome !
    @RequestMapping(value = "/")
    public String index() {
        System.out.println("swagger-ui.html");
        return "redirect:swagger-ui.html";
    }
}

```

This annotation will return a 401 code to an unauthenticated user. If the user is authenticated by 
the **microservice-users-and-teams** microservice, you can retrieve the username of that user in your 
endpoint using :

```
// TODO
```



