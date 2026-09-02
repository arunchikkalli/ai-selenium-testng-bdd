# Setup Guide for AI Selenium TestNG BDD Framework

## System Requirements

- **OS**: Windows, macOS, or Linux
- **Java**: JDK 11 or higher
- **Maven**: 3.6.0 or higher
- **Browsers**: Chrome (latest) or Firefox (latest)
- **RAM**: Minimum 4GB

## Step-by-Step Setup

### Step 1: Install Java

**Windows:**
```bash
# Download from https://www.oracle.com/java/technologies/downloads/
# Set JAVA_HOME environment variable
set JAVA_HOME=C:\Program Files\Java\jdk-11
set PATH=%JAVA_HOME%\bin;%PATH%
```

**macOS/Linux:**
```bash
# Using Homebrew
brew install openjdk@11

# Set JAVA_HOME
export JAVA_HOME=$(/usr/libexec/java_home -v 11)
```

Verify installation:
```bash
java -version
javac -version
```

### Step 2: Install Maven

**Windows:**
```bash
# Download from https://maven.apache.org/download.cgi
# Extract and add to PATH
set PATH=%PATH%;C:\apache-maven-3.9.0\bin
```

**macOS/Linux:**
```bash
brew install maven
```

Verify installation:
```bash
mvn -version
```

### Step 3: Install Git

**Windows:**
```bash
# Download from https://git-scm.com/download/win
```

**macOS:**
```bash
brew install git
```

**Linux:**
```bash
sudo apt-get install git
```

Verify installation:
```bash
git --version
```

### Step 4: Clone Repository

```bash
git clone https://github.com/arunchikkalli/ai-selenium-testng-bdd.git
cd ai-selenium-testng-bdd
```

### Step 5: Install Dependencies

```bash
mvn clean install
```

This will download all required dependencies including:
- Selenium WebDriver
- TestNG
- Cucumber
- WebDriverManager
- Log4j2
- And more...

### Step 6: Configuration Setup

1. Open `src/test/resources/config.json`
2. Update with your application details:

```json
{
  "baseUrl": "https://your-application.com",
  "browser": "chrome",
  "headless": false,
  "implicitWait": 10,
  "explicitWait": 15,
  "screenshotPath": "screenshots"
}
```

### Step 7: Verify Setup

```bash
# Compile the project
mvn clean compile

# Run tests
mvn clean test
```

## IDE Setup

### IntelliJ IDEA

1. **Open Project**:
   - File → Open → Select project folder
   - Choose "Open as Project"

2. **Configure JDK**:
   - File → Project Structure → Project
   - Set SDK to JDK 11
   - Set Language level to 11

3. **Install Plugins**:
   - Plugins → Marketplace
   - Search and install:
     - "Cucumber for Java"
     - "TestNG"
     - "Gherkin"

4. **Maven Configuration**:
   - File → Settings → Build, Execution, Deployment → Maven
   - Set Maven home path

### Eclipse

1. **Import Project**:
   - File → Import → Maven → Existing Maven Projects
   - Select project folder

2. **Configure JDK**:
   - Project → Properties → Java Build Path
   - Set JDK to version 11

3. **Install Plugins**:
   - Help → Eclipse Marketplace
   - Search and install:
     - "Cucumber Eclipse Plugin"
     - "TestNG"

### VS Code

1. **Install Extensions**:
   - Cucumber (Gherkin) Official Extension
   - Test Runner for Java
   - Extension Pack for Java

2. **Configure Maven**:
   - Command Palette → Configure Maven
   - Set Maven home path

## Environment Variables

### Windows (Permanent)

```bash
# Control Panel → System → Environment Variables → New
JAVA_HOME = C:\Program Files\Java\jdk-11
MAVEN_HOME = C:\apache-maven-3.9.0
PATH = %JAVA_HOME%\bin;%MAVEN_HOME%\bin;%PATH%
```

### macOS/Linux (Add to ~/.bash_profile or ~/.zshrc)

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 11)
export MAVEN_HOME=/usr/local/opt/maven
export PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH
```

## Running Tests

### Basic Execution

```bash
# All tests
mvn clean test

# Specific feature
mvn clean test -Dcucumber.features="src/test/resources/features/login.feature"

# Specific tag
mvn clean test -Dcucumber.filter.tags="@smoke"

# Parallel execution
mvn clean test -DthreadCount=4
```

### Headless Execution

```bash
# Update config.json: "headless": true
mvn clean test
```

### Generate Reports

```bash
# Allure Report
mvn clean test
mvn allure:report
mvn allure:serve

# TestNG Report
# Open: target/surefire-reports/index.html

# Cucumber Report
# Open: target/cucumber-reports/index.html
```

## Troubleshooting

### Issue: Java not found
**Solution:**
```bash
# Verify JAVA_HOME is set
java -version

# Add to PATH if needed
export PATH=$JAVA_HOME/bin:$PATH
```

### Issue: Maven build fails
**Solution:**
```bash
# Clear Maven cache
mvn clean

# Update dependencies
mvn dependency:resolve

# Rebuild
mvn clean install
```

### Issue: WebDriver not found
**Solution:**
```bash
# WebDriverManager should download automatically
# If issue persists, manually download from:
# Chrome: https://chromedriver.chromium.org/
# Firefox: https://github.com/mozilla/geckodriver/releases
```

### Issue: Tests timeout
**Solution:**
```bash
# Increase explicit wait in config.json
"explicitWait": 30

# Or set Maven timeout
mvn clean test -DargLine="-Dcom.sun.jndi.ldap.connect.timeout=5000"
```

### Issue: Port already in use (Remote WebDriver)
**Solution:**
```bash
# Check what's using the port
lsof -i :4444  # macOS/Linux
netstat -ano | findstr :4444  # Windows

# Kill the process
kill -9 <PID>  # macOS/Linux
taskkill /PID <PID> /F  # Windows
```

## Next Steps

1. **Read the README**: Understand project structure and features
2. **Write your first test**: Follow the Writing Tests section in README
3. **Explore Examples**: Check the login feature and step definitions
4. **Generate Reports**: Run tests and view Allure reports
5. **CI/CD Setup**: Configure GitHub Actions or your preferred CI tool

## Additional Resources

- [Java Documentation](https://docs.oracle.com/en/java/)
- [Maven Documentation](https://maven.apache.org/guides/)
- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [Cucumber Documentation](https://cucumber.io/docs/cucumber/)

## Support

If you encounter any issues:
1. Check the Troubleshooting section
2. Review logs in `logs/app.log`
3. Check project issues on GitHub
4. Create a new issue with details

---

**Setup Complete! You're ready to start automating! 🚀**
