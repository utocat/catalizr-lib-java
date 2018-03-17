![Java Library for Catalizr API](./img/catalizr.png)

CATALIZR LIBRARY
=================================================
This is a JAVA client library to work with [Catalizr API](https://api.catalizr.io/doc/).

Latest stable version : `0.0.1`

Installation
-------------------------------------------------
SDK has been written in Java 6.

The SDK is published as a Maven artifact on Maven Central Repository (http://search.maven.org/) and can be used with Gradle or Maven.

```
repositories {
    mavenCentral()
}

dependencies {
    compile 'com.utocat:lib-catalizr-java:0.0.1'
}
```

```
<dependency>
  <groupId>com.utocat</groupId>
  <artifactId>lib-catalizr-java</artifactId>
  <version>0.0.1</version>
</dependency>
```

License
-------------------------------------------------
Catalizr Library is distributed under MIT license, see the [LICENSE file](./LICENSE).


Unit Tests
-------------------------------------------------
JUnit tests are placed under
[tests directory](https://github.com/utocat/lib-catalizr-java/tree/master/src/test/java/com/utocat/catalizr).


Contacts
-------------------------------------------------
Report bugs or suggest features using
[issue tracker at GitHub](https://github.com/utocat/lib-catalizr-java/issues).


Account creation
-------------------------------------------------
You can ask an [account](https://www.utocat.com/fr/contact) (note that validation of your sandbox/production account can take a few days, so think about doing it in advance of when you actually want to go live).


Configuration
-------------------------------------------------
Using the credential info from the signup process above, you should then set `catalizr.getConfig().setApiPublicKey` to your Catalizr Public Key and `catalizr.getConfig().setApiPrivateKey` to your Catalizr Private Key.

`catalizr.getConfig().setBaseUrl` is set to preproduction environment by default. To enable production
environment, set it to `https://api.catalizr.io`.
```
    import com.utocat.catalizr.CatalizrApi;

    // ...
	CatalizrApi catalizr = new CatalizrApi();
    
    // configuration    
    catalizr.getConfig().setApiPublicKey("your-public-key");
    catalizr.getConfig().setApiPrivateKey("your-private-key");
    catalizr.getConfig().setBaseUrl("https://preprod.api.catalizr.io");
    catalizr.getConfig().setDebugMode(true);

    // call some API methods...
    List<Id> companies = catalizr.getCompanyApi().getAll(null, null);
```

Sample usage
-------------------------------------------------
```
    import java.util.ArrayList;
	import java.util.List;

	import com.utocat.catalizr.CatalizrApi;
	import com.utocat.catalizr.core.ResponseException;
	import com.utocat.catalizr.entities.Company;
	import com.utocat.catalizr.entities.Id;

    // ...
	CatalizrApi catalizr = new CatalizrApi();
	
    // configuration    
    catalizr.getConfig().setApiPublicKey("your-public-key");
    catalizr.getConfig().setApiPrivateKey("your-private-key");
    catalizr.getConfig().setBaseUrl("https://preprod.api.catalizr.io");
    catalizr.getConfig().setDebugMode(true);

	Company companyToCreate = new Company();
    companyToCreate.setIid("MY_PERSONAL_ID");
	companyToCreate.setName("Catalizr");
	companyToCreate.setLegalForm("SARL");
	companyToCreate.setPhone("0123456789");
	companyToCreate.setAddress("165 Avenue de Bretagne");
    companyToCreate.setZip("59000");
    companyToCreate.setCity("Lille");
    companyToCreate.setCountry("France");
    companyToCreate.setInProgress(false);
    companyToCreate.setSiret("123456789");
       
    try {
       	companyCreated = catalizr.getCompanyApi().create(companyToCreate);
	} catch (ResponseException e) {
		System.out.println(e.getApiMessage());
		System.out.println(e.getResponseHttpCode());
	} catch (Exception e) {
		e.printStackTrace();
	}
```


Follow the changelog
--------------------
You can follow all changes with our [changelog file](./CHANGELOG.md)
