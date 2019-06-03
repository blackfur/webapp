@set UserProfile=d:
@set Program=d:
@call %UserProfile%\env.bat
@set UserProfile=d:

@call %UserProfile%\Android.cmd %*
