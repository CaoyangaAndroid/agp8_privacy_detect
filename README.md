一、app可能存在的隐私合规安全问题，最主要的就是要做到以下两点：
- 在用户同意隐私协议之前，不能有收集用户隐私数据的行为。例如，在用户同意之前不能去获取 Android ID、Device ID、MAC 等隐私数据
- 在用户同意隐私协议之后，搜集用户隐私数据的行为不能超出实现服务场景所必需的最低频率。例如，某些应用会在每次网络请求时将当前设备的 Android ID 作为 header 一起上报，如果没有对 Android ID 进行缓存处理的话，搜集该数据的行为频率就会非常高，此时一样存在隐私合规问题

二、解决方案
- 对于第一点。需要统计出整个项目中所有涉及到隐私行为的相关代码，根据业务流程来判断该隐私行为是否合理、以及是否会在用户同意隐私协议之前被触发。这就需要对整个项目进行 静态扫描 了
- 对于第二点。需要在应用运行时动态记录每次触发隐私行为的时间点和调用链信息，根据触发时间来判断该隐私行为是否过量执行，根据调用链信息来辅助判断具体是哪一块业务在获取隐私数据。这就需要对应用进行 动态记录 了
  以上应对措施如果单纯靠开发人员来肉眼识别代码和编码统计的话，工作量非常大而且也很不现实，因为一个大型项目往往都会引入多个依赖库和第三方 SDK，我们可以规范自有代码，但没法修改和有效约束外部依赖，也很难理清楚依赖库的内部逻辑和调用链关系。此外，当检测到隐私行为后，也要输出相对应的记录报告，以便开发人员能够在开发阶段排查问题。

三、检测sdk介绍
- 对于我们的业务App来说，检测的sdk是完全自研的，完全没有对外出口访问，是符合安全隔离政策的。
- ASM + Transform 字节码插桩的技术方案是非业务侵入式的开发场景，可以实现热插拔，上线前做一次检测，检测完成后，可轻松实现移除依赖。完全不影响线上代码。
- 开发好的检测sdk,已上传至https://jitpack.io/仓库，可以多工程直接复用，适用于在测试环境是使用，生产换进需删除依赖

四、maven集成步骤
1. 设置插件下载源maven ，pluginManagement添加如下：
   maven{
       setUrl("https://jitpack.io")
   }
2. 在工程目录的build.gradle.kts中添加如下内容：
   buildscript {
   dependencies {
   // 使用JitPack生成的完整坐标
   classpath("com.github.CaoyangaAndroid.agp8_privacy_detect:privacy-plugin:41b7888fdf")
   }
   }，在app的build.gradle文件中应用插件plugins {id("com.caoyang.trace.privacy")}
3. 在具体的工程目录下app配置日志输出类，以L包为例extensions.configure<com.example.privacy_detect_plugins.plugins.privacy.PrivacySentryPluginParameter> {
   methodOwner = "com.example.privacydetect.privacy_detect.PrivacySentryRecord"
   methodName = "writeToFile"
   }
4. 添加日志文件输出库依赖implementation(Dependencies.Components.commonsIO)

五、检测并查看检测结果
sync一下工程，运行后，开始走app的流程。检测结果会在/sdcard/Android/data/app的包名/cache文件夹下生成PrivacySentry.txt文件，打开即可查看，调用过的所有敏感api

六、检测后，去除插件（也可采用独立分支方式解集成）
1. 删除四、maven集成步骤中的第1，2，3，4中的内容
2. 也可采用单独创建检测分支的方式，切换开发分支即可