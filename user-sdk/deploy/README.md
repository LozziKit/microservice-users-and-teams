# Deployment

If you're part of the development team of the user-sdk project, you'll want to deploy your
contributions to the repository so that it can be shared with the users of this tool. 

To do so, you'll have to follow these instructions. 

(The original tutorial can be found [Here](https://mymavenrepo.com/docs/maven.html))

## Add a new profile to maven

Add the following profile to your `~/.m2/settings.xml` file.

```xml
<settings>
    <profiles>
        <profile>
            <id>myMavenRepo</id>
            <activation>
                <property>
                    <name>!doNotUseMyMavenRepo</name>
                </property>
            </activation>
            <properties>
                <myMavenRepo.read.url>https://mymavenrepo.com/repo/WqnBJ2iHO7KunsQpuIjD/</myMavenRepo.read.url>
                <myMavenRepo.write.url></myMavenRepo.write.url>
            </properties>
        </profile>
    </profiles>
</settings> 
```

You'll notice the read and write url properties. The read url is a public known url used by the sdk users to download
the maven dependency. The write url however is a trade mark secret that can't be shared. The next step is to contact one of the team members to retrieve that link. Once you have it, add it inside the `myMavenRepo.write.url` property tag in the settings.xml file.

The `pom.xml` file of the **user-sdk** project already contains the configuration to use these properties if you named them exactly the right way.

## Deploy the project

To deploy the project, simply use the command `mvn clean deploy` (or `mvn deploy`).
