package iris.tg.rich

/** Created 22.08.2020
 * @author [Ivan Ivanov](https://vk.com/irisism)
 * */
object IrisTelegramRichText {
	private val htmlStrip = Regex("[<>]")
	fun applyEntities2Html(text: String, entities: List<Entity>): String {
		// stripping tags
		val text = text.replace(htmlStrip) {
			when (it.groupValues[0]) {
				"<" -> "&lt;"
				">" -> "&gt;"
				else -> it.groupValues[0]
			}
		}
		if (entities.isEmpty())
			return text
		val items = mutableListOf<Pair<Int, EntityInfo>>()
		for (e in entities) {
			val type = pairs[e.type]?: continue
			items += e.offset to EntityInfo(type.first, e)
			items += (e.offset + e.length) to EntityInfo(type.second, e)
		}
		items.sortBy { it.first }

		val sb = StringBuilder()
		var curPos = 0
		for ((pos, entityInfo) in items) {
			val ch = CharArray(pos - curPos)
			sb.append(text.toCharArray(ch, 0, curPos, pos))
			when (entityInfo.type) {
				EntityType.AnchorStart -> sb.append("<a href=\"").append(entityInfo.entity.data["url"]).append("\">")
				EntityType.PreStart -> {
					sb.append("<pre")
					val lang = entityInfo.entity.data["language"]?.toString()
					if (!lang.isNullOrBlank())
						sb.append(" language=\"").append(lang).append("\"")
					sb.append('>')
				}
				else -> sb.append(entityInfo.type.html)
			}
			curPos = pos
		}
		if (curPos != text.length) {
			val pos = text.length
			val ch = CharArray(pos - curPos)
			sb.append(text.toCharArray(ch, 0, curPos, pos))
		}

		return sb.toString()
	}

	fun applyEntities2Markdown(text: String, entities: List<Entity>): String {
		if (entities.isEmpty())
			return text
		val items = mutableListOf<Pair<Int, EntityInfo>>()
		for (e in entities) {
			val type = pairs[e.type]?: continue
			items += e.offset to EntityInfo(type.first, e)
			items += (e.offset + e.length) to EntityInfo(type.second, e)
		}
		items.sortBy { it.first }

		val sb = StringBuilder()
		var curPos = 0
		for ((pos, entityInfo) in items) {
			val ch = CharArray(pos - curPos)
			sb.append(text.toCharArray(ch, 0, curPos, pos))
			when (entityInfo.type) {
				EntityType.AnchorEnd -> sb.append("](").append(entityInfo.entity.data["url"]).append(')')
				EntityType.PreStart -> {
					sb.append("```")
					val lang = entityInfo.entity.data["language"]?.toString()
					if (!lang.isNullOrBlank())
						sb.append(lang)
				}
				else -> sb.append(entityInfo.type.markdown)
			}
			curPos = pos
		}
		if (curPos != text.length) {
			val pos = text.length
			val ch = CharArray(pos - curPos)
			sb.append(text.toCharArray(ch, 0, curPos, pos))
		}

		return sb.toString()
	}

	class Entity(val type: String, val offset: Int, val length: Int, val data: Map<String, Any?>) {
		override fun toString(): String {
			return "Entity(type='$type', offset=$offset, length=$length, data=$data)"
		}
	}

	class EntityInfo(val type: EntityType, val entity: Entity) {
		override fun toString(): String {
			return "EntityInfo(type=$type, entity=$entity)"
		}
	}

	enum class EntityType(val html: String, val markdown: String = "") {
		BoldStart("<b>", "*"), BoldEnd("</b>", "*"),
		ItalicStart("<i>", "_"), ItalicEnd("</i>", "_"),
		UnderlineStart("<u>", "__"), UnderlineEnd("</u>", "__"),
		StrikethroughStart("<s>", "~"), StrikethroughEnd("</s>", "~"),
		AnchorStart("<a>", "["), AnchorEnd("</a>", "]")
		, CodeStart("<code>", "`"), CodeEnd("</code>", "`")
		, PreStart("<pre>", "```"), PreEnd("</pre>", "```");

		override fun toString(): String {
			return "EntityType(html='$html', markdown='$markdown')"
		}


	}

	private val pairs = mapOf<String, Pair<EntityType, EntityType>>(
		"bold" to (EntityType.BoldStart to EntityType.BoldEnd)
		, "italic" to (EntityType.ItalicStart to EntityType.ItalicEnd)
		, "underline" to (EntityType.UnderlineStart to EntityType.UnderlineEnd)
		, "strikethrough" to (EntityType.StrikethroughStart to EntityType.StrikethroughEnd)
		, "text_link" to (EntityType.AnchorStart to EntityType.AnchorEnd)
		, "code" to (EntityType.CodeStart to EntityType.CodeEnd)
		, "pre" to (EntityType.PreStart to EntityType.PreEnd)
	)
}

// To test features
fun main() {
	val text = """🗓 Правила беседы:
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

Нарушения выдаются модераторами по усмотрению. yyyy"""

	val entitiesTxt = """{offset=19, length=86, type=code}
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
{offset=1210, length=46, type=italic}"""

	val reg = Regex("\\{offset=(\\d+), length=(\\d+), type=(\\w+)(.*)\\}")
	val entities = mutableListOf<IrisTelegramRichText.Entity>()
	for (m in reg.findAll(entitiesTxt)) {
		val (_, offset, length, type, other) = m.groupValues
		val otherMap = if (other.isEmpty()) emptyMap<String, Any?>()
		else {
			other.trim(',', ' ').split(',').associate {
				val (key, value) = it.trim().split('=')
				key to value
			}
		}
		entities += IrisTelegramRichText.Entity(type, offset.toInt(), length.toInt(), otherMap)
	}
	val res = IrisTelegramRichText.applyEntities2Markdown(text, entities)
	println("MARKDOWN:")
	println(res)
	println()
	val res2 = IrisTelegramRichText.applyEntities2Html(text, entities)
	println("HTML:")
	println(res2)
}