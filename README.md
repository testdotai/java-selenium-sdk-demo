# test-ai-selenium-demo
[![JDK-11+](https://img.shields.io/badge/JDK-11%2B-blue)](https://adoptium.net)
[![Apache 2.0](https://img.shields.io/badge/Apache-2.0-blue)](https://www.apache.org/licenses/LICENSE-2.0)
[![Discord](https://img.shields.io/discord/853669216880295946?&logo=discord)](https://sdk.test.ai/discord)

Welcome to the test.ai Selenium Java demo!

This repository contains a pre-configured project and basic tutorial, so you can hit the ground running with [test.ai enhanced Selenium](https://github.com/testdotai/java-selenium-sdk)!

👉 This tutorial uses Google Chrome, but you will be able to use `test-ai-selenium` with any other browser that supports Selenium.

## Pre-requisites
Before we get started, please ensure that you have installed the following software on your computer:

* [Java](https://adoptium.net) - version `11` or newer
* [Google Chrome](https://www.google.com/chrome/)

You will also need a test.ai account, please visit https://sdk.test.ai (it's free!) to register.

## An Introduction to the test.ai SDK
In the following tutorial, you will learn how to set up and run AI-enhanced Selenium with test.ai.

Now that you have the pre-requisites installed, let's get started.

Please begin by cloning this repository to your computer.
```bash
git clone https://github.com/testdotai/java-selenium-sdk-demo.git
```

### Run the demo
Please visit https://sdk.test.ai, and log into your account.  Please copy your `API key` (in the upper right corner of your screen), you will need this for the next step.

![Example API Key](https://testdotai.github.io/static-assets/appium-demo/api_key.png)

Next, `cd` into the root directory of this repository and run the following command, replacing the text `YOUR_API_KEY` with your test.ai API key.
```bash
./gradlew run --args=YOUR_API_KEY
```

If using Windows, please run
```powershell
gradlew.bat run --args=YOUR_API_KEY
```

If everything worked, an instance of the Chrome browser will be started and open to the [test.ai Store](https://testaistore.com) (no, it's not a real store!).  Then, the sample code in this demo will click the `Store` navbar link, search for `Shoes`, and add a pair of green shoes to the cart.

🎥 Click [here](https://testdotai.github.io/static-assets/selenium-demo/no_ai_example_flow.mov) to see a video of the expected behavior.

Thus far, the demo is using the standard Selenium selectors without any AI, which is how apps today are commonly tested.  However, selectors such as these are fragile and break easily, as even minor changes to a website may cause them to *immediately* stop working.  Fortunately, the test.ai SDK is equipped to help you avoid this unecessary hassle.

### Using test.ai with Selenium

Please visit https://sdk.test.ai (and log in to your test.ai account if you've been logged out).

You should see the following new entries on this page:

<img src="https://testdotai.github.io/static-assets/selenium-demo/element_list.png" width=400>

namely,
* `store_nav_link`
* `search_products_input`
* `search_button`
* `green_shoes`
* `add_to_cart`

Start by clicking on the link in the `Element` column for `store_nav_link`.

On this new page, find the navbar link `Store`.  Using your mouse, click and drag a box around the link.  A green box will appear as you drag your mouse.  Release your mouse button to save the selection.

![store nav link demo](https://testdotai.github.io/static-assets/selenium-demo/store_nav_link.gif)

Believe it or not, you just used AI!  test.ai is visual-based, so there's no need to mess around with Selenium selectors.  The test.ai classifier will train itself using the element inside the box you just drew with your mouse, and now, when it encounters this element in the future, it will be able to recognize it!  

Let's do the same thing for the other elements.

#### `search_products_input`
<details>
  <summary>(click to expand)</summary>
  
![search products input demo](https://testdotai.github.io/static-assets/selenium-demo/search_products_input.gif)
</details>

#### `search_button`
<details>
  <summary>(click to expand)</summary>
  
![search button demo](https://testdotai.github.io/static-assets/selenium-demo/search_button.gif)
</details>

#### `green_shoes`
<details>
  <summary>(click to expand)</summary>
  
![green shoes demo](https://testdotai.github.io/static-assets/selenium-demo/green_shoes.gif)
</details>

#### `add_to_cart`
<details>
  <summary>(click to expand)</summary>
  
![add to cart demo](https://testdotai.github.io/static-assets/selenium-demo/add_to_cart.gif)
</details>

👉 Training takes a few minutes, you can check training status by visiting https://sdk.test.ai/training_status

![training status](https://testdotai.github.io/static-assets/selenium-demo/training_status.png)

Next, let's simulate what happens when a developer changes a web application's code.

In the IDE of your choice, please open [src/main/java/ai/test/sdk/demo/Example.java](src/main/java/ai/test/sdk/demo/Example.java).  This Java file contains an abridged form of what you might find in typical Selenium-based test suite.

A few noteworthy items:
* The `ChromeDriver` gets passed as a parameter to a `TestAiDriver`, along with your API key.
* Each call to a "`findElementsBy`" method contains a second parameter, which is used to give the element an optional, human-readable label for use at https://sdk.test.ai

As you can see, it is very easy to integrate test.ai into your existing Selenium-based test cases.

Now, let's change a couple of the XPath selectors:

```java
// Change this line:
WebElement storeNavLink = driver.findElementById("menu-item-45", "store_nav_link");

//to:
WebElement storeNavLink = driver.findElementById("my-developers-changed-this", "store_nav_link");
```

```java
// Change this line:
WebElement searchProductsInput = driver.findElementByCssSelector("#woocommerce-product-search-field-0", "search_products_input");

// to:
WebElement searchProductsInput = driver.findElementByCssSelector("#my-developers-also-changed-this", "search_products_input");
```

```java
// Change this line:
WebElement addToCart = driver.findElementByXPath("//*[@id=\"product-2719\"]/div[2]/form/button", "add_to_cart");

// to:
WebElement addToCart = driver.findElementByXPath("//*[wow/these/developers/have/been/busy]", "add_to_cart");
```

As you may have already guessed, a standard Selenium test would certainly fail, but test.ai won't!

### Re-run using AI selectors
Using your terminal, `cd` into the root directory of this project, and run the following command, replacing the text `YOUR_API_KEY` with your test.ai API key.

```bash
./gradlew run --args=YOUR_API_KEY
```

If using Windows, please run
```powershell
gradlew.bat run --args=YOUR_API_KEY
```

The demo will repeat the same steps that it performed previously, only this time, it's using test.ai!  The "developer's breaking changes" we introduced in the code have no effect on the test's ability to run, all thanks to test.ai AI.

And that's all folks, you've made it to the end of the tutorial!  🎉

As far as next steps go, please feel free to experiment more with this code and try out some of the other selectors.  Good luck and have fun!

## Additional Resources
* [API docs](https://www.javadoc.io/doc/ai.test.sdk/test-ai-selenium)
* [Another Basic Tutorial](https://sdk.test.ai/tutorial)

## Contact
Questions?  Comments?  We'd love to hear from you!

* ✉️ Email us: `sdk {at} test.ai`
* 💬 Chat with us on Discord: https://sdk.test.ai/discord