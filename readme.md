# Telegram Bot as SpringBoot starter app


## How to start

###  Telegram Token

You have to change the the config values for `bot.token` and  `bot.username` and `weather` in the `application.yml` to the credentials of your bot and then 
you're ready to go.

Plz rename _./src/main/resources/application.example_ to _./src/main/resources/application.yml_ and write you data.
Make your Bot by https://telegram.me/BotFather

### Weather Token 
https://home.agromonitoring.com/api_keys


### Telegram Bot as SpringBoot starter app

You have to change the the config values for `bot.token` and  `bot.username` in the `application.yml` to the credentials of your bot and then 
you're ready to go.

To start in Docker container, first build the image:
```
docker build -t bot .
```
then run:
```
docker run -d --rm -it bot:latest
```
