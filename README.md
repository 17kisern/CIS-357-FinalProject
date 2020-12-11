# CIS-357-FinalProject

#### Prerequisites

- AWS Account
- Base Knowledge of Android Studio

#### Setup Guide

1. Create a new Android Studio project
	1. Use the "Empty Activity" template
	1. Use a minimum SDK of 23 or higher
		1. This is required for the Drop-In UI to wor
1. Install the AWS CLI either for Windows CMD or Bash
https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2.html
1. Install Amplify CLI
https://docs.amplify.aws/cli/start/install
1. Run command `amplify init`, and follow the prompts, using default values for everything EXCEPT
	1.  When prompted, you must go through the process of creating an IAM User with an Access ID and Secret Key
		1.  The `amplify init` command will forward you to the AWS Console to create this IAM USER
1. Run `amplify add auth` and follow the prompts, using default values
	1. Here we are telling amplify to add a functionality, that functionality being authentication
1. Run `amplify push` and follow the prompts, using default values
	1. Here we are telling amplify to actually perform all the setup we've configured into the cloud (AWS)

#### Android Studio Guide

Now we're ready to begin working in the actual Android Studio project. The majority of the work necessary (configuring the cloud) has already been completed.

AWS has a very helpful "Drop-In" UI, that allows you the developer, to focus more on whatever functionality your app will have, and less on the UI's interactions with Authentication. So the primary configuration needed within Android Studio is simply to tell it to run our Login Portal BEFORE running the actual app (change the launcher).

1. Add the following dependencies to your app's build.gradle file
	1. ```
	    // AWSMobileClient
	    implementation 'com.amazonaws:aws-android-sdk-mobile-client:2.13.+'

	    // Cognito UserPools for SignIn
	    implementation 'com.amazonaws:aws-android-sdk-auth-userpools:2.13.+'

	    // Drop in UI provided by AWS
	    implementation 'com.amazonaws:aws-android-sdk-auth-ui:2.13.+'```
1. Change the `AndroidManifest.xml` file such that it launches the Login Portal by default
	1. This will result in some errors (don't worry), as we haven't created the AuthenticationActivity yet
	1. Specifically note lines 12 through 24 that have changed
	1. ```
	<?xml version="1.0" encoding="utf-8"?>
	<manifest xmlns:android="http://schemas.android.com/apk/res/android"
		package="com.cis357.finalproject">

		<application
			android:allowBackup="true"
			android:icon="@mipmap/ic_launcher"
			android:label="@string/app_name"
			android:roundIcon="@mipmap/ic_launcher_round"
			android:supportsRtl="true"
			android:theme="@style/Theme.FinalProject">
			<activity
				android:name=".AuthenticationActivity"
				android:noHistory="true">
				<intent-filter>
					<action android:name="android.intent.action.MAIN" />

					<category android:name="android.intent.category.LAUNCHER" />
				</intent-filter>
			</activity>
			<activity
				android:name=".MainActivity"
				android:label="@string/app_name"></activity>
		</application>

	</manifest>
	```
1. Create the AuthenticationActivity by adding a new "Empty Activity" to the project, called `AuthenticationActivity`
	1. For this activity, we don't actually have to touch any UI, as the "Drop In" UI handles that all for us
1. Configure the "Drop-In" UI
	1. Reference [`AuthenticationActivity.kt`]() in the project files
1. 
