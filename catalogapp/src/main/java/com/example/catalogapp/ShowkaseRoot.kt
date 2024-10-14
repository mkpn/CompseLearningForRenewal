package com.example.catalogapp

import com.airbnb.android.showkase.annotation.ShowkaseRoot
import com.airbnb.android.showkase.annotation.ShowkaseRootModule

// 別module内のUIだけでカタログを作る場合でも、
// 拡張関数であるShowkase.getBrowserIntentにアクセスしてカタログAPP用UIを生成するために
// ShowkaseRootを定義する必要がある
@ShowkaseRoot
class ShowkaseRoot : ShowkaseRootModule