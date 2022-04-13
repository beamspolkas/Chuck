package com.example.chuck.events

class Events {
    // Event used to send message from fragment to activity.
    class FragmentToActivityMessage(val message: String)

    // Event used to send message from activity to fragment.
    class ActivityToFragmentMessage(val message: String)

    // Event used to send message from activity to activity.
    class ActivityActivityMessage(val message: String)
}