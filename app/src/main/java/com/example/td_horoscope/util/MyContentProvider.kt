package com.example.td_horoscope.util

import android.Manifest
import com.example.td_horoscope.R
import com.example.td_horoscope.bean.IconTitleBean

/**
 * @name td_horoscope
 * @class name：com.example.td_horoscope.util
 * @class describe
 * @author wujinming QQ:1245074510
 * @time 2020/10/28 11:28
 * @class describe
 */
object MyContentProvider {

    val permissionList = arrayListOf(
        IconTitleBean(R.mipmap.per_device, "为您提供精确的扫描、清理服务", "存储")
    )


    val homeHoroscope = arrayListOf(
        IconTitleBean(R.mipmap.icon_xz_baiyang, Contents.BAIYANG, "03.21-04.19")
        , IconTitleBean(R.mipmap.icon_xz_jinniu, Contents.JINGNIU, "04.20-05.20")
        , IconTitleBean(R.mipmap.icon_xz_shuangzi, Contents.SHUANGZI, "05.21-06.21")
        , IconTitleBean(R.mipmap.icon_xz_juxie, Contents.JUXIE, "06.22-07.22")
        , IconTitleBean(R.mipmap.icon_xz_shizi, Contents.SHIZI, "07.23-08.22")
        , IconTitleBean(R.mipmap.icon_xz_chunv, Contents.CHUNV, "08.23-09.22")
        , IconTitleBean(R.mipmap.icon_xz_tianping, Contents.TINGPING, "09.23-10.22")
        , IconTitleBean(R.mipmap.icon_xz_tianxie, Contents.TIANXIE, "10.23-11.21")
        , IconTitleBean(R.mipmap.icon_xz_sheshou, Contents.SHESHOU, "11.22-12.21")
        , IconTitleBean(R.mipmap.icon_xz_mojie, Contents.MOJIE, "12.22-01.19")
        , IconTitleBean(R.mipmap.icon_xz_shuiping, Contents.SHUIPING, "01.20-02.18")
        , IconTitleBean(R.mipmap.icon_xz_shuangyu, Contents.SHUANGYU, "02.19-03.20")
    )

    //爱情运
    val love_baiyang =
        "单纯直率的白羊座，对生活对爱情都充满着不灭的激情，白羊的爱情就像他们的性格一样，乐观、积极，白羊的情感能让你感到前所未有的欢愉。但由于性格过分执着，经常会有偏激的行动，这为你们在谈恋爱时很容易就会犯蠢。\n" +
                "尤其是在白羊女很爱很爱一个人的时候，除了爱连对方的身份背景都模糊掉。白羊没有那么多复杂的心思，爱就是爱，不爱就是不爱，不会为了外在的原因和不爱的人在一起，也不会因为别人的眼光与相爱的人分离，乃至于会情不自禁地迷恋已婚男，甚至还蠢蠢地等待对方离婚来跟你在一起"
    val love_jinniu =
        "内敛保守的金牛座，比白羊城府要深点，而且做事也相较于理智一些，金牛座的爱情是经过深思熟虑的，他们不会轻易爱上一个人，即使爱，也不会表达出来，他们会做一系列的分析，确定对方确实是自己想要的人才会有所行动。\n" +
                "金牛过于物质化，导致对金钱的迷恋日益渐深，而在谈恋爱的时候，金牛女甚至会以对方的经济条件作为首要考虑因素，若对方没有经济基础，那么，金牛绝对不会将心思放在对方的身上。因为金牛女天性较为贪婪，容易被金钱满足物质的欲望，所以只要对方愿意大把大把地花钱在你身上，就会误认为是爱情。"
    val love_shuangzi =
        "天真乐观的双子座，外表机灵活泼，再加上风趣幽默的个性，容易获得异性的心，双子的异性缘向来很好，也因此，双子座的爱情过于理想化，他们总认为自己是人群中的焦点，大家爱着自己是理所当然的事。\n" +
                "虽然你们貌美如花，能够让异性神魂颠倒，但若你就此认定为这些人对你的爱坚如磐石或者是一心一意，那就错了！因为青春长驻仅仅是美好的愿望，每个人的容颜终会衰老。而且世上漂亮女生那么多，谁能保证他下一秒喜欢的还是你呢？过于自信不但会令你失去良缘，还会让你惨遭孤独的下场。"
    val love_juxie =
        "在踏入社会之后，工作场所便是巨蟹座的第二个家，巨蟹是一个十分踏实的人，他们知道只有努力工作才能让生活更加的美满。巨蟹座的爱情通常都是细水长流的，一见钟情这种事很少发生在她们的身上，这并不符合他们的性格，巨蟹的爱情通常是在了解中逐渐产生的。\n" +
                "以巨蟹慢热型的个性，很容易会陷入日久生情的办公室恋爱中，但是办公室恋情并不是人人都经营得起，尤其是当你把这份工作当事业的时候。因为假如恋情失败，恐怕就会影响到自己的工作，乃至前程。若是没有把握，请不要以试试玩玩的心态去开始。"
    val love_shizi =
        "霸气侧漏就是指狮子座女生，她们天生就有着能够震慑众人的威严，也因为她们性格的原因，狮子座的爱情并不容易得到。这个时代的男生都是喜欢小鸟依人的女生，过于强势，会让他们有压力和压迫感，因此，狮子女在恋爱中则显得非常无力。\n" +
                "或许一开始，对方会认为你是个很有个性的人，对你产生情愫，但久而久之就会被你那强势又好胜的个性而吓跑。而且爱面子的狮子女，看着对方远离却放不下架子退让，即便撒谎也企图用强硬的方式挽留他，或许尝试低头看看别人，也许会让你发现另一道不一样的风景。"
    val love_chunv =
        "处女座女生最容易就是犯自恋的毛病，很难想象谨慎的处女们竟然会轻易相信别人的甜言蜜语，也或许这是所有女生的通病，处女们只要别人稍赞两句，她们就开始自作多情，认定对方一定对自己有意思。其实，那或许仅仅只是别人出于礼貌的话罢了，尤其是在面对心仪对象或者成功人士的时候，更容易被对方的一句夸奖而春心荡漾。\n" +
                "此时，处女女就会不顾一切的找机会回应对方的“心意”，到最后发现是自作多情，处女女便会打入万丈深渊而痛苦不已。若处女女真心喜欢一个人，不如多些观察再决定是否向对方表达自己的心意。"
    val love_tianping =
        "天秤座的爱情不像他们的性格，他们对于爱情是坚定不移的，一旦爱便深爱，至死不渝。虽然天秤经常犹豫不决，但对于认定的爱情却能粉身碎骨，乃至忘记自己的本性。专情的天秤座，一开始恋爱脑子就从没出现过“分手”两字，就算爱情之路坎坷不堪，他们也会尽自己所能坚持到底。\n" +
                "天秤的感情属于死到临头都不悔改的类型，所以天秤才会如此全心全意地倾尽所有去经营一段恋情。但并不是用心经营就能够开花结果的，有时也要考虑自己和对方是否幸福，若因爱情让双方都感到痛苦，那爱情就会变成枷锁，别高估了爱情的力量。"
    val love_tianxie =
        "高冷的天蝎座，其实很容易听信男人的花言巧语，甚至把整颗心都搭上去，这是她们的死穴。天蝎座的爱情是最为深情的，也许她们并不是那么容易爱上一个人，但一旦她们拥有了爱情，便会将爱进行到底。\n" +
                "天蝎不喜欢墨守陈规的生活模式，若是当对方能够迎合你们的口味制定长远而新鲜的计划，天蝎女就会容易沉溺其中，享受对方构筑的美好未来。但是别忘了，男人在哄女生时说的山盟海誓都只是迎合罢了，又或许是亏心事后的补偿，爱虽然美好，但需要多些理智去判别世间的一切，过于信任自己认为该信任的人，只会令自己陷入无尽的痛苦深渊之中。"
    val love_sheshou =
        "活泼乐天的射手座，天生就像是只脱缰的野马一样，情绪飘忽不定，很多都认为射手座是个对待爱情十分不专心的人，爱情对于他们而言，不过是冒险旅程中的其中一项挑战性的项目罢了。但其实射手座的爱情并不像外表那样漫不经心。\n" +
                "在爱情中，射手最常犯的愚蠢事就是自欺欺人，不管对方当初骗你是因为财迷心窍还是其他，你都心甘情愿被欺骗，但被骗的可怜之人也有可恨的地方，而你们多数都是自找的，但真正可悲的是当你知道对方的欺骗后，仍旧大度地忍气吞声，甚至配合出现这出荒诞剧。"
    val love_mojie =
        "格外沉着稳重的摩羯座，对事业富有野心，做事则低调，不喜欢引起别人的注意，在爱情的世界中，摩羯的仍不改风格。摩羯座的爱情像温暖的春风，像雨天的一把伞，平时也许没人感觉得到，但在最重要的时刻，他们就会出现，在你需要他们的时候，他们会不顾一切的出现在对方的面前。\n" +
                "摩羯总是默默付出，希望对方能够自己察觉并为之感动，然后得到他的尊重和赞赏，但是一味的迎合别人的想法，慢慢失去自我，这样的爱情该如何长久的维持下去呢？相信对方喜欢的是最真实的你，轻易改变自己，只不过是变相伤害自己罢了。"
    val love_shuiping =
        "常常有人想，水瓶座爱情会是怎样的呢？想必是独树一帜的吧，否则怎能搭配得起他们的性格呢？其实不然，水瓶在感情的道路上并不是那么的顺利，他们心中藏有太多的不肯定因素，影响着自己，也影响着那些深爱他们的人。\n" +
                "水瓶在谈恋爱的时候显得相当笨拙，他们似乎对爱情这件事十分缺乏安全感，即便很优秀也缺乏自信心，所以只要一点风吹草动就会杯弓蛇影地胡乱猜忌，而这恰恰是爱情中最忌讳的事情。如此下去，只会加深对方的厌恶情绪。还不如用聪明的脑袋想想，让他用行动来证明这份爱吧！"
    val love_shuangyu =
        "双鱼是一个为爱而生的星座，他们把爱情看得比任何东西都重要，在他们眼里，世上最重要的事无非就是爱情，对重要的人就是他们的爱人，这就是双鱼座的爱情，拥有坚定不移信念的爱情。\n" +
                "在恋爱的时候，双鱼会不顾一切地爱着对方，乃至于对方身上再不好的缺点，双鱼也会尽全力去包容，去迁就，即使知道这样会伤害到自己，他们仍然会不顾一切。但是总是处于卑微的地位的爱情，始终很难维持长久。唯有从美好的幻想中清醒过来，保持冷静的头脑去思考这份感情吧！别忘了，除了爱情，你们还有梦想。"


    val homeContext = arrayListOf(
        IconTitleBean(R.mipmap.icon_sxpd, "生肖配对"),
        IconTitleBean(R.mipmap.icon_hp, "合盘"),
        IconTitleBean(R.mipmap.icon_cjx, "QQ测吉凶"),
        IconTitleBean(R.mipmap.icon_zgjm, "周公解梦")
    )

    val dateContent = arrayListOf(
        IconTitleBean(title = Contents.TODAY, hint = "今天"),
        IconTitleBean(title = Contents.TOMORROW, hint = "明天"),
        IconTitleBean(title = Contents.WEEK, hint = "本周"),
        IconTitleBean(title = Contents.MONTH, hint = "本月"),
        IconTitleBean(title = Contents.YEAR, hint = "本年"),
        IconTitleBean(title = Contents.LOVE, hint = "爱情")
    )


    val settingList = arrayListOf(
        IconTitleBean(R.mipmap.icon_idea, "意见反馈"),
        IconTitleBean(R.mipmap.icon_about, "关于我们"),
        IconTitleBean(R.mipmap.icon_kefu, "联系邮箱  2681706890@qq.com")
    )

    //生肖
    val zodiacList = arrayListOf(
        IconTitleBean(R.mipmap.icon_xs_shu, Contents.SHU, "1")
        , IconTitleBean(R.mipmap.icon_xs_niu, Contents.NIU, "2")
        , IconTitleBean(R.mipmap.icon_xs_hu, Contents.HU, "3")
        , IconTitleBean(R.mipmap.icon_xs_tu, Contents.TU, "4")
        , IconTitleBean(R.mipmap.icon_xs_long, Contents.LONG, "5")
        , IconTitleBean(R.mipmap.icon_xs_she, Contents.SHE, "6")
        , IconTitleBean(R.mipmap.icon_xs_ma, Contents.MA, "7")
        , IconTitleBean(R.mipmap.icon_xs_yang, Contents.YANG, "8")
        , IconTitleBean(R.mipmap.icon_xs_hou, Contents.HOU, "9")
        , IconTitleBean(R.mipmap.icon_xs_ji, Contents.JI, "10")
        , IconTitleBean(R.mipmap.icon_xs_gou, Contents.GOU, "11")
        , IconTitleBean(R.mipmap.icon_xs_zhu, Contents.ZHU, "12")
    )

    val permissions = arrayListOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

}