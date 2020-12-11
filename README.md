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
1. Within the AWS Console (online browser), navigate to the IAM service, and create an IAM User with an Access ID and Secret Key (Write these two values down, they will be used in the next step
	1. When choosing permissions for this IAM User, for development purposes, I gave it complete admin credentials, the same as what I have in AWS under the Admin account, however you SHOULD NEVER do this in an actual production use case.
1. Run command `amplify init`, and follow the prompts, using default values for everything 
	1. When prompted for if you want to use an AWS Profile, say yes
		1. Follow the prompts to connect the IAM User you just created (Using the Access ID and Secret Key) to give Amplify permission to operate on behalf of this IAM User
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
	    implementation 'com.amazonaws:aws-android-sdk-auth-ui:2.13.+'
	    ```
1. Change the [`AndroidManifest.xml`](app/src/main/AndroidManifest.xml) file such that it launches the Login Portal by default
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
1. Configure the "Drop In" UI
	1. Reference [`AuthenticationActivity.kt`](app/src/main/java/com/cis357/finalproject/AuthenticationActivity.kt) in the project files
	1. We are -
		1. Initializing the *AWSMobileClient*
		1. Creating a callback so it knows what to do depending on what state our user is in
			1. If Signed In  -> Load the MainActivity
			1. If Signed Out -> Show the "Drop In" UI
1. Adjust the UI view of the Main Activity so that the user has the ability to sign out
	1. Reference [`activity_main.xml`](app/src/main/res/layout/activity_main.xml)
	1. Add a TextView resource and refactor it's ID to be *"usernameText"*
	1. Add a Button resource and refactor it's ID to be *"signOutButton"*
1. Adjust the code of the Main Activity so that our app takes action on these newly created resources
	1. Reference [`MainActivity.kt`](app/src/main/java/com/cis357/finalproject/MainActivity.kt)
	1. Cache references to the TextView and the Button we just created
	1. Create a listener on the *"signOutButton"* that 
		1. Resets the *"usernameText"* to show as "Guest"
		1. Calls the signout functionality in the *AWSMobileClient*
		1. Creates an Intent and transitions to a different activity (the [`AuthenticationActivity.kt`](app/src/main/java/com/cis357/finalproject/AuthenticationActivity.kt))


# AND THAT'S IT, YOU'RE DONE
Launch your app to test and see if you did everything right!
