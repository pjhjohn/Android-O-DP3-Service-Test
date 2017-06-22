# Android O ServiceTest

### Hypothesis

`MainService` is killed by system 1 min after `MainReceiver` no longer registers Alarm with AlarmManager

### Usage

Modify number of initial Intent to `MainReceiver` and watch LogCat
