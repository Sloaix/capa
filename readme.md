# Capa [![](https://jitpack.io/v/lsxiao/capa.svg)](https://jitpack.io/#lsxiao/capa)
<a href="http://www.methodscount.com/?lib=com.github.lsxiao.capa%3Acapa%3A0.1.2"><img src="https://img.shields.io/badge/Methods count-core: 93 | deps: 5492-e91e63.svg"/></a>
<a href="http://www.methodscount.com/?lib=com.github.lsxiao.capa%3Acapa%3A0.1.2"><img src="https://img.shields.io/badge/Size-13 KB-e91e63.svg"/></a>

a lightly state layout for android.

![gif](https://raw.githubusercontent.com/lsxiao/capa/master/demo.gif)

## Start

quick integration with 3 minutes

### integration

use jitpack in your module.
```groovy
allProjects {
  repositories {
    maven { url "https://www.jitpack.io" }
  }
}
```

depend these in your build.gralde.

```groovy
dependencies {
  compile 'com.github.lsxiao:capa:1.0.4'
}
```

## Usage

### init

 use `com.lsxiao.capa.CapaLayout` to wrap your own content layout or view.

 cp_state is the attribute for first show state layout

 default is load state(`load`,`empty`,`error`,`content`)
```xml
<com.lsxiao.capa.CapaLayout
    android:id="@+id/capa_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"

    app:cp_anim_in="@anim/capa_fade_in"
    app:cp_anim_out="@anim/capa_fade_out"

    app:cp_empty_layout="@layout/capa_empty_layout"
    app:cp_error_layout="@layout/capa_error_layout"
    app:cp_load_layout="@layout/capa_load_layout"

    app:cp_anim_enable="true"
    app:cp_state="load">

    <!-- your content layout or view-->
    <TextView
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

</com.lsxiao.capa.CapaLayout>
```

### switch state
```java
cataLayout.toLoad();

cataLayout.toEmpty();

cataLayout.toError();

cataLayout.toContent();
```

### animation
#### not show animation
animation default is enabled.

use `app:cp_anim_enable="false"` to change,or use animNone() method in your code.

```java
capaLayout.animNone();//not show animation.
```

#### default inner animation
```java
capaLayout.animFade();

capaLayout.animeSlideInTop();

capaLayout.animeSlideInBottom();
```

#### custom animation
```java
capaLayout.animIn(...);

capaLayout.animOut(...);
```

or

```xml
app:cp_anim_in="..."
app:cp_anim_out="..."
```

### get state view
```java
capaLayout.getLoadView();

capaLayout.getEmptyView();

capaLayout.getErrorView();

capaLayout.getContentView();
```

## How to contribute

welcome pr.

## Versioning
We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/lsxiao/capa/tags).

## Authors

* **lsxiao** - *Android developer* - [lsxiao](https://github.com/lsxiao)
See also the list of [contributors](https://github.com/lsxiao/capa/contributors) who participated in this project.

## License

MIT
