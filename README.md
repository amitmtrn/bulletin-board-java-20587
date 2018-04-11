# Forum Project

## pre installation

[install nodejs](https://nodejs.org/en/download/package-manager/)

install java
```
sudo apt-get install default-jdk
```

install maven
```
sudo apt-get install maven
```

## installation

download the project
```
git clone https://github.com/amitmtrn/bulletin-board-java-20587.git
```

### client folder - client side
install the client side
```
cd client
npm install
```

build the client project
```
npm run build
```

### root folder - server side
run maven project
```
mvn spring-boot:run
```