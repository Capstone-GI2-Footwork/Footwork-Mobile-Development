import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gi2.footwork.R
import com.gi2.footwork.ui.theme.onPrimaryFixed
import com.gi2.footwork.ui.theme.primaryFixed
import com.gi2.footwork.ui.theme.unselectedNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView

@Composable
//@Preview
fun BottomNavigationBar() {
    var selectedItem by remember { mutableStateOf("Home") }

    @Composable
    fun NavigationItem(
        iconRes: Int,
        contentDescription: String,
        isSelected: Boolean,
        onClick: () -> Unit
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = contentDescription,
                modifier = Modifier.size(24.dp),
                tint = if (isSelected) {
                    primaryFixed
                } else {
                    unselectedNavigation
                }
            )
        }
    }

    @Composable
    fun NavigationBar(
        selectedItem: String,
        onItemSelected: (String) -> Unit,
        modifier: Modifier = Modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .shadow(elevation = 12.dp)
                .background(color = onPrimaryFixed, shape = RoundedCornerShape(size = 16.dp))
                .padding(vertical = 4.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                NavigationItem(
                    iconRes = R.drawable.ic_bottombar_home,
                    contentDescription = "Home",
                    isSelected = selectedItem == "Home",
                    onClick = { onItemSelected("Home") }
                )

                NavigationItem(
                    iconRes = R.drawable.ic_bottombar_leaderboard,
                    contentDescription = "Leaderboard",
                    isSelected = selectedItem == "Leaderboard",
                    onClick = { onItemSelected("Leaderboard") }
                )

                Spacer(modifier = Modifier.width(40.dp))

                NavigationItem(
                    iconRes = R.drawable.ic_bottombar_tracking,
                    contentDescription = "Tracking",
                    isSelected = selectedItem == "Tracking",
                    onClick = { onItemSelected("Tracking") }
                )

                NavigationItem(
                    iconRes = R.drawable.ic_bottombar_community,
                    contentDescription = "Community",
                    isSelected = selectedItem == "Community",
                    onClick = { onItemSelected("Community") }
                )
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
    ) {
        NavigationBar(
            selectedItem = selectedItem,
            onItemSelected = { newSelection ->
                selectedItem = newSelection

                /* TODO */
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        )

        IconButton(
            onClick = { /* TODO */ },
            modifier = Modifier
                .padding(bottom = 24.dp)
                .size(56.dp)
                .clip(CircleShape)
                .background(color = primaryFixed)
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bottombar_main),
                contentDescription = "Main Content",
                modifier = Modifier.size(24.dp),
                tint = onPrimaryFixed
            )
        }
    }
}

@Preview
@Composable
private fun BottomNavigationBarPrev() {
    BottomNavigationBar()
}