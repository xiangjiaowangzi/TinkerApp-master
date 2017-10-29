# TinkerApp #

本项目使用Tinker进行热修复的过程；

1.运行项目；


> ![](https://i.imgur.com/ezDjQZ7.png)

2.此时将编译生产在bakApk包下的文件替换gradle中的对应版本；

	ext {
    tinkerEnabled = true
    // 基础版本apk
    tinkerOldApkPath = "${bakPath}/mytinker-debug-20170629-16-41-16.apk"
    //proguard mapping file to build patch apk
    tinkerApplyMappingPath = "${bakPath}/"
    // 与基础版本一起生成的R.text文件
    tinkerApplyResourcePath = "${bakPath}/mytinker-debug-20170629-16-41-16-R.txt"
    //only use for build all flavor, if not, just ignore this field
    tinkerBuildFlavorDirectory = "${bakPath}/"
	}

3.运行脚本，得到patch_signed_7zip.apk

![](https://i.imgur.com/PzOECFb.png)
![](https://i.imgur.com/doj2OvD.png)

4.根据代码中的路径，patch_signed_7zip.apk放在手机中对应路径

		String patch = Environment.getExternalStorageDirectory()+ File.separator+"tinker" + "/patch_signed_7zip.apk" ;

5.最后执行加载补丁

![](https://i.imgur.com/X8VwAVJ.png)


