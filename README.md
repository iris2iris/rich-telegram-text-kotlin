# Rich Telegram text for Kotlin
Applies rich text format with Telegram entities (such as **bold**, _italic_, underline, and [links](https://github.com/iris2iris/rich-telegram-text-kotlin)) to plain text. 

✅ The results of execution can be either `HTML-markup` or `Markdown` code.

Example text:

![Example text](https://iris-tg.ru/images/ddd.png)

Plain text
```
🗓 Правила беседы:
Беседа строго для вопросов по функционалу Ириса, обсуждений и предложений по развитию.
 
Запрещается:
1. Оффтоп: скидывание сообщений не по теме чата. 
2. Флуд: бессмысленные сообщения, набор смайлов, букв, стикеров, эмодзи, пишете лестницей (по одному слову), флуд командами бота.
3. Спам: пиар, реклама, подозрительные ссылки. Сюда же относится просьба поставить лайк/проголосовать, взаимная подписка и т.п.
4. Оскорбления лёгкого/грубого характера. 
5. Намеренное нарушение и игнорирование замечаний/правил.
6. Введение в заблуждение неоднократное кол-во раз.
7. Разглашение или открытая продажа рп-команд. (не касается вип рп и мрп).
8. Неадекватное девиантное поведение, разжигание срачей.
9. Контент, не подходящий большей целевой аудитории: треш-контент, шок-контент, эротический контент и т.п.
10. Обход бана: забанены будут все твинки, с которых совершён обход. 
11. Добавление и использование страничных ботов без согласия администрации.
12. Вызов модераторов без причины / без ув. причины.
13. Намеренное введение в заблуждение с особо крупным ущербом для бесед. (кик неактив минута, например)
14. Использовать zalgo и другие символы-/вложения-убийцы.
15. Мошеннические проекты.

Нарушения выдаются модераторами по усмотрению. yyyy
```

Entities set:
```
{offset=19, length=86, type=code}
{offset=124, length=7, type=bold}
{offset=174, length=5, type=bold}
{offset=304, length=5, type=bold}
{offset=432, length=38, type=bold}
{offset=475, length=54, type=bold}
{offset=533, length=48, type=bold}
{offset=585, length=43, type=bold}
{offset=660, length=53, type=bold}
{offset=717, length=49, type=bold}
{offset=825, length=11, type=bold}
{offset=895, length=71, type=bold}
{offset=971, length=48, type=bold}
{offset=1024, length=68, type=bold}
{offset=1128, length=53, type=bold}
{offset=1186, length=22, type=bold}
{offset=1210, length=46, type=italic}
```

## Generating HTML markup
Execute method: `IrisTelegramRichFormat.applyEntities2Html(text, entities)`

Result:
```
🗓 Правила беседы:
<code>Беседа строго для вопросов по функционалу Ириса, обсуждений и предложений по развитию.</code>
 
Запрещается:
1. <b>Оффтоп:</b> скидывание сообщений не по теме чата. 
2. <b>Флуд:</b> бессмысленные сообщения, набор смайлов, букв, стикеров, эмодзи, пишете лестницей (по одному слову), флуд командами бота.
3. <b>Спам:</b> пиар, реклама, подозрительные ссылки. Сюда же относится просьба поставить лайк/проголосовать, взаимная подписка и т.п.
4. <b>Оскорбления лёгкого/грубого характера.</b> 
5. <b>Намеренное нарушение и игнорирование замечаний/правил.</b>
6. <b>Введение в заблуждение неоднократное кол-во раз.</b>
7. <b>Разглашение или открытая продажа рп-команд.</b> (не касается вип рп и мрп).
8. <b>Неадекватное девиантное поведение, разжигание срачей.</b>
9. <b>Контент, не подходящий большей целевой аудитории:</b> треш-контент, шок-контент, эротический контент и т.п.
10. <b>Обход бана:</b> забанены будут все твинки, с которых совершён обход. 
11. <b>Добавление и использование страничных ботов без согласия администрации.</b>
12. <b>Вызов модераторов без причины / без ув. причины.</b>
13. <b>Намеренное введение в заблуждение с особо крупным ущербом для бесед.</b> (кик неактив минута, например)
14. <b>Использовать zalgo и другие символы-/вложения-убийцы.</b>
15. <b>Мошеннические проекты.</b>

<i>Нарушения выдаются модераторами по усмотрению.</i> yyyy

Process finished with exit code 0
```

## Generating Markdown code
Execute method: `IrisTelegramRichText.applyEntities2Markdown(text, entities)`

```
🗓 Правила беседы:
`Беседа строго для вопросов по функционалу Ириса, обсуждений и предложений по развитию.`
 
Запрещается:
1. *Оффтоп:* скидывание сообщений не по теме чата. 
2. *Флуд:* бессмысленные сообщения, набор смайлов, букв, стикеров, эмодзи, пишете лестницей (по одному слову), флуд командами бота.
3. *Спам:* пиар, реклама, подозрительные ссылки. Сюда же относится просьба поставить лайк/проголосовать, взаимная подписка и т.п.
4. *Оскорбления лёгкого/грубого характера.* 
5. *Намеренное нарушение и игнорирование замечаний/правил.*
6. *Введение в заблуждение неоднократное кол-во раз.*
7. *Разглашение или открытая продажа рп-команд.* (не касается вип рп и мрп).
8. *Неадекватное девиантное поведение, разжигание срачей.*
9. *Контент, не подходящий большей целевой аудитории:* треш-контент, шок-контент, эротический контент и т.п.
10. *Обход бана:* забанены будут все твинки, с которых совершён обход. 
11. *Добавление и использование страничных ботов без согласия администрации.*
12. *Вызов модераторов без причины / без ув. причины.*
13. *Намеренное введение в заблуждение с особо крупным ущербом для бесед.* (кик неактив минута, например)
14. *Использовать zalgo и другие символы-/вложения-убийцы.*
15. *Мошеннические проекты.*

_Нарушения выдаются модераторами по усмотрению._ yyyy
```