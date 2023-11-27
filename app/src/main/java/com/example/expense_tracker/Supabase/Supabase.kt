package com.example.expense_tracker.Supabase

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest

object Supabase {

    val client = createSupabaseClient(
        supabaseUrl = "https://wvdkuwcawujulrlknzcn.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Ind2ZGt1d2Nhd3VqdWxybGtuemNuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDA4NDE5NjAsImV4cCI6MjAxNjQxNzk2MH0.7SnOFDKwAercO-o4owtXSfIeJiRhorfhp9rHLjOyUN4"
    ) {
        install(GoTrue)
        install(Postgrest)
    }
}